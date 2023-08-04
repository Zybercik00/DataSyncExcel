package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.zybercik00.entity.metadata.*;
import com.github.zybercik00.entity.process.Extraction;
import com.github.zybercik00.entity.process.Waste;
import com.github.zybercik00.repository.metadata.MappingRepo;
import com.github.zybercik00.repository.proces.ExtractionRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.*;

@RequiredArgsConstructor
public class ExtractionService {
    private final ExtractionRepo extractionRepo;
    private final MappingRepo mappingRepo;
    private final MappingService mappingService;
    private final ExcelTableFactory excelTableFactory;
    private final ObjectMapper objectMapper;

    @Transactional
    Map<String, MappingAttribute> getMappings() throws JsonProcessingException {
        Map<String, MappingAttribute> mappings = new LinkedHashMap<>();
        List<MappingEntity> mappingEntities = mappingRepo.findAll();
        for (MappingEntity mapping : mappingEntities) {
            AttributeEntity target = mapping.getTarget();
            String source = mapping.getSource();
            MappingAttribute attribute = getMappingAttribute(target);
            mappings.put(source, attribute);
        }
        return mappings;
    }


    @Transactional
    public List<Extraction> getExtractions(XSSFSheet sheet) throws JsonProcessingException {
        List<Extraction> extractions = new ArrayList<>();
        ExcelTableWithHeader excelTableWithHeader = excelTableFactory.getExcelTable(sheet);
        Map<String, MappingAttribute> mappings = getMappings();
        ExcelTableWithHeader.Cursor cursor = excelTableWithHeader.cursor();
        while (cursor.next()) {
            Extraction extraction = getExtraction(cursor, mappings);
            extractions.add(extraction);
        }
        return extractions;
    }

    @SneakyThrows
    private Extraction getExtraction(ExcelTableWithHeader.Cursor cursor, Map<String, MappingAttribute> mappings) {
        Extraction extraction = getExtraction();
        for (Map.Entry<String, MappingAttribute> entry : mappings.entrySet()) {
            String source = entry.getKey();
            MappingAttribute attribute = entry.getValue();
            mappingService.setValue(
                    cursor,
                    extraction,
                    source,
                    attribute
            );
        }
        // TODO Use batch updates
        return extractionRepo.save(extraction);
    }

    private MappingAttribute getMappingAttribute(AttributeEntity entity) throws JsonProcessingException {
        if (entity instanceof SimpleAttributeEntity attribute) {
            return new SimpleMappingAttribute(attribute.getTargetProperty());
        }
        if (entity instanceof ReferenceAttributeEntity attribute) {
            return ReferenceMappingAttribute.builder()
                    .targetProperty(attribute.getTargetProperty())
                    .nestedProperty(attribute.getNestedProperty())
                    .build();
        }
        if (entity instanceof QualifiedAttributeEntity attribute) {
            Map<String, Object> qualifier = getQualifier(attribute);
            return QualifierMappingAttribute.builder()
                    .targetProperty(attribute.getTargetProperty())
                    .qualifierProperty(attribute.getQualifierProperty())
                    .qualifierParent(attribute.getQualifierParent())
                    .qualifier(qualifier)
                    .build();
        }
        throw new AssertionError();
    }

    private Map<String, Object> getQualifier(QualifiedAttributeEntity attribute) throws JsonProcessingException {
        List<QualifierValueEntity> attributeValues = attribute.getValues();
        return toMap(attributeValues);
    }

    private Map<String, Object> toMap(List<QualifierValueEntity> attributeValues) throws JsonProcessingException {
        Map<String, Object> map = new LinkedHashMap<>();
        for (QualifierValueEntity attributeValue : attributeValues) {
            String code = attributeValue.getCode();
            JsonNode jsonNode = attributeValue.getValue();
            Object value = objectMapper.treeToValue(jsonNode, Object.class);
            map.put(code, value);
        }
        return map;
    }

    private Object toValue(JsonNode value) {
        if (value instanceof ArrayNode node) {
            return toList(node);
        }
        if (value instanceof ObjectNode node) {
            return toMap(node);
        }
        return value.asText();
    }

    private List<Object> toList(ArrayNode node) {
        List<Object> values = new ArrayList<>(node.size());
        for (JsonNode jsonNode : node) {
            values.add(toValue(jsonNode));
        }
        return values;
    }

    private Map<String, Object> toMap(ObjectNode node) {
        Set<Map.Entry<String, JsonNode>> properties = node.properties();
        Map<String, Object> values = new LinkedHashMap<>();
        for (Map.Entry<String, JsonNode> property : properties) {
            String key = property.getKey();
            JsonNode jsonNode = property.getValue();
            values.put(key, toValue(jsonNode));
        }
        return values;
    }

    private Extraction getExtraction() {
        // TODO Should be also incremental
        Extraction extraction = new Extraction();
        extraction.setWaste(new Waste());
        extraction.setPurchasePrices(new ArrayList<>());
        extraction.setSalePrices(new ArrayList<>());
        return extractionRepo.save(extraction);
    }

}