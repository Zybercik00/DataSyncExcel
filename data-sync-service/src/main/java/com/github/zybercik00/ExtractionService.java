package com.github.zybercik00;

import com.github.zybercik00.domain.proces.*;
import com.github.zybercik00.repository.proces.ExtractionRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ExtractionService {
    private final ExtractionRepo extractionRepo;
    private final MappingServive mappingServive;
    private final ExcelTableFactory excelTableFactory;

    @Transactional
    public List<Extraction> getExtractions(XSSFSheet sheet) {
        List<Extraction> extractions = new ArrayList<>();
        ExcelTableWithHeader excelTableWithHeader = excelTableFactory.getExcelTable(sheet);
        ExcelTableWithHeader.Cursor cursor = excelTableWithHeader.cursor();
        while (cursor.next()) {
            Extraction extraction = getExtraction(cursor);
            extractions.add(extraction);
        }
        return extractions;
    }

    @SneakyThrows
    private Extraction getExtraction(ExcelTableWithHeader.Cursor cursor) {
        Extraction extraction = getExtraction();
        // TODO Use list of value mappers from excel to entity
        // TODO Handle missing value
        {
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "material",
                    Map.of("lot", "Lot"));
            // TODO depends on material mapping
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "material.name",
                    "Name");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "preparedOn",
                    "Made on");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "weightBefore",
                    "Weight before");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "weightAfter",
                    "Weight after");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "waste.lossAfterExtractionInKg",
                    "Loss kg");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "waste.lossAfterExtractionInPercent",
                    "Loss %");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "realizedBy",
                    Map.of("name", "Prepared by"));
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "receivedBackOn",
                    "Received back");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "sampleTestResult",
                    "Result of the tested sample");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "waste.packedKg",
                    "Packed kg");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "waste.lossTotalKg",
                    "Aggregate loss kg");
            mappingService.setProperty(
                    cursor,
                    extraction,
                    "waste.lossTotalPercents",
                    "Aggregate loss %");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "purchasePrices",
                    Map.of(
                            "extraction", extraction,
                            "currency", Map.of("code", "EUR")
                    ),
                    "purchasePrice",
                    "Purchase price EUR");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "purchasePrices",
                    Map.of(
                            "extraction", extraction,
                            "currency", Map.of("code", "CHF")
                    ),
                    "purchasePrice",
                    "Purchase price CHF");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "10% marge")
                    ),
                    "salePrice",
                    "Sale price 10% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "20% marge")
                    ),
                    "salePrice",
                    "Sale price 20% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "30% marge")
                    ),
                    "salePrice",
                    "Sale price 30% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "40% marge")
                    ),
                    "salePrice",
                    "Sale price 40% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "50% marge")
                    ),
                    "salePrice",
                    "Sale price 50% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "60% marge")
                    ),
                    "salePrice",
                    "Sale price 60% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "70% marge")
                    ),
                    "salePrice",
                    "Sale price 70% marge");
            mappingService.setComponentValue(
                    cursor,
                    extraction,
                    "salePrices",
                    Map.of(
                            "extraction", extraction,
                            "margin", Map.of("name", "100% marge")
                    ),
                    "salePrice",
                    "Sale price 100% marge");
            return extractionRepo.save(extraction);
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