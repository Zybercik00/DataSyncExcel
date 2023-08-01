package com.github.zybercik00;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MappingAttributeService {
    private final Map<String, MappingAttribute> attributeMap;

    public MappingAttributeService() {
        Map<String, MappingAttribute> attributeMap = new HashMap<>();

        attributeMap.put("material", ReferenceMappingAttribute.builder()
                .targetProperty("material")
                .nestedProperty("lot")
                .build());
        attributeMap.put("realizedBy", ReferenceMappingAttribute.builder()
                .targetProperty("realizedBy")
                .nestedProperty("name")
                .build());

        attributeMap.put("material.name", new SimpleMappingAttribute("material.name"));
        attributeMap.put("preparedOn", new SimpleMappingAttribute("preparedOn"));
        attributeMap.put("weightBefore", new SimpleMappingAttribute("weightBefore"));
        attributeMap.put("weightAfter", new SimpleMappingAttribute("weightAfter"));
        attributeMap.put("waste.lossAfterExtractionInKg", new SimpleMappingAttribute("waste.lossAfterExtractionInKg"));
        attributeMap.put("waste.lossAfterExtractionInPercent", new SimpleMappingAttribute("waste.lossAfterExtractionInPercent"));
        attributeMap.put("receivedBackOn", new SimpleMappingAttribute("receivedBackOn"));
        attributeMap.put("sampleTestResult", new SimpleMappingAttribute("sampleTestResult"));
        attributeMap.put("waste.packedKg", new SimpleMappingAttribute("waste.packedKg"));
        attributeMap.put("waste.lossTotalKg", new SimpleMappingAttribute("waste.lossTotalKg"));
        attributeMap.put("waste.lossTotalPercents", new SimpleMappingAttribute("waste.lossTotalPercents"));

        attributeMap.put("purchasePrices[currency.code='EUR']", QualifierMappingAttribute.builder()
                .targetProperty("purchasePrices")
                .qualifier(Map.of("currency", Map.of("code", "EUR")))
                .qualifierProperty("purchasePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("purchasePrices[currency.code='CHF']", QualifierMappingAttribute.builder()
                .targetProperty("purchasePrices")
                .qualifier(Map.of("currency", Map.of("code", "CHF")))
                .qualifierProperty("purchasePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='10% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "10% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='20% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "20% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='30% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "30% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='40% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "40% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='50% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "50% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='60% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "60% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='70% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "70% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        attributeMap.put("salePrices[margin.name='100% marge']", QualifierMappingAttribute.builder()
                .targetProperty("salePrices")
                .qualifier(Map.of("margin", Map.of("name", "100% marge")))
                .qualifierProperty("salePrice")
                .qualifierParent("extraction")
                .build());
        this.attributeMap = Map.copyOf(attributeMap);
    }

    public MappingAttribute getAttribute(String path) {
        return Objects.requireNonNull(attributeMap.get(path), path);
    }
}