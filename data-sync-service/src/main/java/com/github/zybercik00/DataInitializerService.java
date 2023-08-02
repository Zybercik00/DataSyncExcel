package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.domain.mappingAttribute.MappingAttributeEntity;
import com.github.zybercik00.domain.mappingAttribute.QualifierMappingAttributeEntity;
import com.github.zybercik00.domain.mappingAttribute.ReferenceMappingAttributeEntity;
import com.github.zybercik00.repository.mappingattribute.MappingAttributeRepo;
import com.github.zybercik00.repository.mappingattribute.QualifierMappingAttributeRepo;
import com.github.zybercik00.repository.mappingattribute.ReferenceMappingAttributeRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataInitializerService {

    private final MappingAttributeRepo mappingAttributeRepo;
    private final ReferenceMappingAttributeRepo referenceMappingAttributeRepo;
    private final QualifierMappingAttributeRepo qualifierMappingAttributeRepo;
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
            MappingAttributeEntity mappingAttribute = new MappingAttributeEntity();
            mappingAttribute.setPath(path);
            mappingAttributeRepo.save(mappingAttribute);
        }

        List<Map<String, String>> referenceMappings = Arrays.asList(
                Map.of("targetProperty", "material", "nestedProperty", "lot"),
                Map.of("targetProperty", "realizedBy", "nestedProperty", "name")
        );

        for (Map<String, String> mapping: referenceMappings) {
            ReferenceMappingAttributeEntity referenceEntity = new ReferenceMappingAttributeEntity();
            referenceEntity.setTargetProperty(mapping.get("targetProperty"));
            referenceEntity.setNestedProperty(mapping.get("nestedProperty"));
            referenceMappingAttributeRepo.save(referenceEntity);

        }

        List<Map<String, String>> qualifierMappings = Arrays.asList(
                Map.of("targetProperty", "purchasePrices", "qualifierProperty", "purchasePrice",
                        "qualifierParent", "extraction", "qualifier", "{\"currency\":{\"code\":\"EUR\"}}")
        );

        for (Map<String, String> mapping: qualifierMappings) {
            QualifierMappingAttributeEntity qualifierEntity = new QualifierMappingAttributeEntity();
            qualifierEntity.setTargetProperty(mapping.get("targetProperty"));
            qualifierEntity.setQualifierProperty(mapping.get("qualifierProperty"));
            qualifierEntity.setQualifierParent(mapping.get("qualifierParent"));
            try {
                qualifierEntity.setQualifier(objectMapper.writeValueAsString(mapping.get("qualifier")));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            qualifierMappingAttributeRepo.save(qualifierEntity);
        }

    }
}
