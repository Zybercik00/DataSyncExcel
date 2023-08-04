package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.domain.mappingAttribute.MappingEntity;
import com.github.zybercik00.domain.mappingAttribute.QualifierAttributeEntity;
import com.github.zybercik00.domain.mappingAttribute.ReferenceAttributeEntity;
import com.github.zybercik00.domain.mappingAttribute.SimpleAttributeEntity;
import com.github.zybercik00.repository.mappingattribute.MappingRepo;
import com.github.zybercik00.repository.mappingattribute.AttributeRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataInitializerService {

    private final MappingRepo mappingRepo;
    private final AttributeRepo attributeRepo;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void initializeData() {
        List<String> mappingPaths = Arrays.asList(
                "material.name",
                "preparedOn",
                "weightBefore",
                "weightAfter",
                "waste.lossAfterExtractionInKg",
                "waste.lossAfterExtractionInPercent",
                "receivedBackOn",
                "sampleTestResult",
                "waste.packedKg",
                "waste.lossTotalKg",
                "waste.lossTotalPercents"
        );

        for (String path : mappingPaths) {
            MappingEntity mappingAttribute = new MappingEntity();
            mappingAttribute.setSource(path);
            mappingRepo.save(mappingAttribute);
        }

        List<Map<String, String>> referenceMappings = Arrays.asList(
                Map.of("targetProperty", "material", "nestedProperty", "lot"),
                Map.of("targetProperty", "realizedBy", "nestedProperty", "name")
        );

        for (Map<String, String> mapping: referenceMappings) {
            ReferenceAttributeEntity referenceEntity = new ReferenceAttributeEntity();
            referenceEntity.setTargetProperty(mapping.get("targetProperty"));
            referenceEntity.setNestedProperty(mapping.get("nestedProperty"));
            referenceMappingAttributeRepo.save(referenceEntity);

        }

        List<Map<String, String>> qualifierMappings = Arrays.asList(
                Map.of("targetProperty", "purchasePrices", "qualifierProperty", "purchasePrice",
                        "qualifierParent", "extraction", "qualifier", "{\"currency\":{\"code\":\"EUR\"}}")
        );

        for (Map<String, String> mapping: qualifierMappings) {
            QualifierAttributeEntity qualifierEntity = new QualifierAttributeEntity();
            qualifierEntity.setTargetProperty(mapping.get("targetProperty"));
            qualifierEntity.setQualifierProperty(mapping.get("qualifierProperty"));
            qualifierEntity.setQualifierParent(mapping.get("qualifierParent"));
            try {
                qualifierEntity.setQualifier(objectMapper.writeValueAsString(mapping.get("qualifier")));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            attributeRepo.save(qualifierEntity);
        }

    }
}
