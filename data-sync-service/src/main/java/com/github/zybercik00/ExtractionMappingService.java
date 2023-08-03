package com.github.zybercik00;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ExtractionMappingService {

    @Getter
    private final List<Mapping> mappings;

    public ExtractionMappingService() {
        List<Mapping> mappings = new ArrayList<>();
        mappings.add(Mapping.builder()
                .target("material")
                .source("Lot")
                .build());
        mappings.add(Mapping.builder()
                .target("material.name")
                .source("Name")
                .build());
        mappings.add(Mapping.builder()
                .target("preparedOn")
                .source("Made on")
                .build());
        mappings.add(Mapping.builder()
                .target("weightBefore")
                .source("Weight before")
                .build());
        mappings.add(Mapping.builder()
                .target("weightAfter")
                .source("Weight after")
                .build());
        mappings.add(Mapping.builder()
                .target("waste.lossAfterExtractionInKg")
                .source("Loss kg")
                .build());
        mappings.add(Mapping.builder()
                .target("waste.lossAfterExtractionInPercent")
                .source("Loss %")
                .build());
        mappings.add(Mapping.builder()
                .target("realizedBy")
                .source("Prepared by")
                .build());
        mappings.add(Mapping.builder()
                .target("receivedBackOn")
                .source("Received back")
                .build());
        mappings.add(Mapping.builder()
                .target("sampleTestResult")
                .source("Result of the tested sample")
                .build());
        mappings.add(Mapping.builder()
                .target("waste.packedKg")
                .source("Packed kg")
                .build());
        mappings.add(Mapping.builder()
                .target("waste.lossTotalKg")
                .source("Aggregate loss kg")
                .build());
        mappings.add(Mapping.builder()
                .target("waste.lossTotalPercents")
                .source("Aggregate loss %")
                .build());
        mappings.add(Mapping.builder()
                .target("purchasePrices[currency.code='EUR']")
                .source("Purchase price EUR")
                .build());
        mappings.add(Mapping.builder()
                .target("purchasePrices[currency.code='CHF']")
                .source("Purchase price CHF")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='10% marge']")
                .source("Sale price 10% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='20% marge']")
                .source("Sale price 20% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='30% marge']")
                .source("Sale price 30% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='40% marge']")
                .source("Sale price 40% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='50% marge']")
                .source("Sale price 50% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='60% marge']")
                .source("Sale price 60% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='70% marge']")
                .source("Sale price 70% marge")
                .build());
        mappings.add(Mapping.builder()
                .target("salePrices[margin.name='100% marge']")
                .source("Sale price 100% marge")
                .build());
        this.mappings = List.copyOf(mappings);
    }
}