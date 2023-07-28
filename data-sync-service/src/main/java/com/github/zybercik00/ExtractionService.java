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

@RequiredArgsConstructor
public class ExtractionService {
    private final ExtractionRepo extractionRepo;
    private final MaterialService materialService;
    private final EmployeeService employeeService;
    private final PurchasePriceService purchasePriceService;
    private final SalePriceService salePriceService;
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
            Material material = materialService.getMaterial(lot);
            extraction.setMaterial(material);
        }
        {
            String name = cursor.getStringValue("Name");
            BeanUtils.setProperty(extraction, "material.name", name);
        }
        // TODO Use BeanUtils to make mapping generic
        {
            Date daterowValue = cursor.getDateValue("Made on");
            extraction.setPreparedOn(daterowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Weight before");
            extraction.setWeightBefore(numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Weight after");
            extraction.setWeightAfter(numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Loss kg");
            extraction.getWaste().setLossAfterExtractionInKg(numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Loss %");
            extraction.getWaste().setLossAfterExtractionInPercent(numericrowValue);
        }
        {
            String stringrowValue = cursor.getStringValue("Prepared by");
            Employee employee = employeeService.getEmployee(stringrowValue);
            extraction.setRealizedBy(employee);
        }
        {
            Date daterowValue = cursor.getDateValue("Received back");
            extraction.setReceivedBackOn(daterowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Result of the tested sample");
            extraction.setSampleTestResult(numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Packed kg");
            extraction.getWaste().setPackedKg(numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Aggregate loss kg");
            extraction.getWaste().setLossTotalKg(numericrowValue);
        }
        {
            BigDecimal numericrowValue = cursor.getNumericValue("Aggregate loss %");
            extraction.getWaste().setLossTotalPercents(numericrowValue);
        }
        {
            BigDecimal price = cursor.getNumericValue("Purchase price EUR");
            addPurchasePrice(extraction, "EUR", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Purchase price CHF");
            addPurchasePrice(extraction, "CHF", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 10% marge");
            addSalePrice(extraction, "10% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 20% marge");
            addSalePrice(extraction, "20% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 30% marge");
            addSalePrice(extraction, "30% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 40% marge");
            addSalePrice(extraction, "40% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 50% marge");
            addSalePrice(extraction, "50% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 60% marge");
            addSalePrice(extraction, "60% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 70% marge");
            addSalePrice(extraction, "70% marge", price);
        }
        {
            BigDecimal price = cursor.getNumericValue("Sale price 100% marge");
            addSalePrice(extraction, "100% marge", price);
        }
        return extractionRepo.save(extraction);
    }

    private Extraction getExtraction() {
        // TODO Should be also incremental
        Extraction extraction = new Extraction();
        extraction.setWaste(new Waste());
        extraction.setPurchasePrices(new ArrayList<>());
        extraction.setSalePrices(new ArrayList<>());
        return extraction;
    }

    private void addPurchasePrice(Extraction extraction, String currencyCode, BigDecimal price) {
        PurchasePrice purchasePrice = purchasePriceService.getPurchasePrice(extraction, currencyCode, price);
        extraction.getPurchasePrices()
                .add(purchasePrice);
    }

    private void addSalePrice(Extraction extraction, String marginName, BigDecimal price) {
        SalePrice salePrice = salePriceService.getSalePrice(extraction, marginName, price);
        extraction.getSalePrices()
                .add(salePrice);
    }

}