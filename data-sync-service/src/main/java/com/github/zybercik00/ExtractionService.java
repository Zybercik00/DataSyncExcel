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
    private final EntityService entityService;
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
            String lot = cursor.getStringValue("Lot");
            Material material = entityService.findEntityBy(Material.class, Map.of("lot", lot));
            BeanUtils.setProperty(extraction, "material", material);
        }
        {
            // TODO depends on material mapping
            String name = cursor.getStringValue("Name");
            BeanUtils.setProperty(extraction, "material.name", name);
        }

        {
            Date daterowValue = cursor.getDateValue("Made on");
            BeanUtils.setProperty(extraction, "preparedOn", daterowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Weight before");
            BeanUtils.setProperty(extraction, "weightBefore", numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Weight after");
            BeanUtils.setProperty(extraction, "weightAfter", numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Loss kg");
            BeanUtils.setProperty(extraction, "waste.lossAfterExtractionInKG", numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Loss %");
            BeanUtils.setProperty(extraction, "waste.lossAfterExtractionInPercent", numericrowValue);
        }
        {
            String stringrowValue = cursor.getStringValue("Prepared by");
            Employee employee = entityService.findEntityBy(Employee.class, Map.of("name", stringrowValue));
            BeanUtils.setProperty(extraction, "realizedBy", employee);
        }
        {
            Date daterowValue = cursor.getDateValue("Received back");
            BeanUtils.setProperty(extraction, "recivedBackOn", daterowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Result of the tested sample");
            BeanUtils.setProperty(extraction, "sampleTestResult", numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Packed kg");
            BeanUtils.setProperty(extraction, "waste.packedKg", numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Aggregate loss kg");
            BeanUtils.setProperty(extraction, "waste.lossTotalKg", numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Aggregate loss %");
            BeanUtils.setProperty(extraction, "waste.lossTotalPercents", numericrowValue);
        }
        {
            BigDecimal price = cursor.getNumericValue("Purchase price EUR");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "currency", Map.of("code", "EUR"));
            PurchasePrice purchasePrice = entityService.findEntityBy(PurchasePrice.class, lookup);
            BeanUtils.setProperty(purchasePrice, "purchasePrice", price);
            extraction.getPurchasePrices().add(purchasePrice);

        }
        {
            BigDecimal price = cursor.getNumericValue("Purchase price CHF");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "currency", Map.of("code", "CHF"));
            PurchasePrice purchasePrice = entityService.findEntityBy(PurchasePrice.class, lookup);
            BeanUtils.setProperty(purchasePrice, "purchasePrice", price);
            extraction.getPurchasePrices().add(purchasePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 10% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "10% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 20% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "20% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 30% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "30% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 40% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "40% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 50% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "50% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 60% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "60% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 70% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "70% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 100% marge");
            Map<String, Object> lookup = Map.of("extraction", extraction,
                    "margin", Map.of("name", "100% marge"));
            SalePrice salePrice = entityService.findEntityBy(SalePrice.class, lookup);
            BeanUtils.setProperty(salePrice, "salePrice", price);
            extraction.getSalePrices().add(salePrice);
        }
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