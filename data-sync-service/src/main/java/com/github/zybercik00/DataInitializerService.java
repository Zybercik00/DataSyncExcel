package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.entity.metadata.*;
import com.github.zybercik00.repository.mappingattribute.AttributeRepo;
import com.github.zybercik00.repository.mappingattribute.MappingRepo;
import com.github.zybercik00.repository.mappingattribute.ReferenceRepo;
import com.github.zybercik00.repository.mappingattribute.SimpleAttributeEntityRepo;
import com.github.zybercik00.repository.mappingattribute.QualifierAttributeEntityRepo;
import com.github.zybercik00.repository.mappingattribute.ReferenceAttributeEntityRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataInitializerService {

    private final AttributeRepo attributeRepo;
    private final MappingRepo mappingRepo;
    private final ReferenceRepo referenceRepo
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

        List<Map<String, String>> referenceMapping = Arrays.asList(
                Map.of("targetProperty", "material", "nestedProperty", "lot"),
                Map.of("targetProperty", "realizedBy", "nestedProperty", "name")
        );

        for (Map<String, String> mapping : referenceMapping) {
            ReferenceAttributeEntity referenceEntity = new ReferenceAttributeEntity();
            referenceEntity.setTargetProperty(mapping.get("targetProperty"));
            referenceEntity.setNestedProperty(mapping.get("nestedProperty"));
            referenceRepo.save(referenceEntity);

        }

        List<Map<String, String>> qualifierMapping = Arrays.asList(
                Map.of("targetProperty", "purchasePrice",
                        "qualifierProperty", "purchasePrice",
                        "qualifierParent", "extraction",
                        "qualifier", "{\"currency\":{\"code\":\"EUR\"}}")
        );

        for (Map<String, String> mapping : qualifierMapping) {
            QualifierAttributeEntity qualifierEntity = new QualifierAttributeEntity();
            qualifierEntity.setTargetProperty(mapping.get("targetProperty"));
            qualifierEntity.setQualifierProperty(mapping.get("qualifierProperty"));
            qualifierEntity.setQualifierParent(mapping.get("qualifierParent"));
            try {
                qualifierEntity.setQualifier()
            }
            )
        }


    }
}
