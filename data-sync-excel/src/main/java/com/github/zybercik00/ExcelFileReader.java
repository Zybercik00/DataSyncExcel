package com.github.zybercik00;

import com.github.zybercik00.domain.proces.*;
import com.github.zybercik00.domain.proces.Currency;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@RequiredArgsConstructor
public class ExcelFileReader  implements CommandLineRunner {

    private final MaterialService materialService;
    private final EmployeeService employeeService;
    private final MarginService marginService;
    private final ExcelSheetProperties properties;
    private final DataFormatter dataFormatter;
    private URL excelResourceUrl;

    void initService() throws IOException {
        checkResourceUrl();
    }

    private void checkResourceUrl() throws IOException {
        ClassLoader classLoader = ExcelFileReader.class.getClassLoader();
        excelResourceUrl = classLoader.getResource(properties.getExcelResource());
        if (excelResourceUrl == null) {
            throw new IOException("Resource not found: " + properties.getExcelResource());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        readListExcelFile();
    }

    private void readListExcelFile() throws IOException {
        // TODO Use service and repository
        Map<String, Employee> employees = new HashMap<>();

        List<String> lines = new ArrayList<>();
        try (InputStream inputStream = excelResourceUrl.openStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(properties.getSheetName());

            Map<Integer, String> header = new HashMap<>();
            Iterable<Row> rowIterator = sheet::rowIterator;
            int rowIndex = 1;
            for (Row row : rowIterator) {
                if (row == null) {
                    continue;
                }
                if (rowIndex == 1) {
                    // TODO Parse header
                    fillHeader(header, row);
                } else {
                    Extraction extraction = cellToLine(employees, header, row);
                    lines.add(extraction.toString());
                }
                rowIndex++;
            }
        }
        lines.forEach(System.out::println);
    }

    private void fillHeader(Map<Integer, String> header, Row row) {
        int columnIndex = 1;
        Iterable<Cell> cells = row::cellIterator;
        for (Cell cell : cells) {
            header.put(columnIndex, dataFormatter.formatCellValue(cell).trim());
            columnIndex++;
        }
    }

    private Extraction cellToLine(
            Map<String, Employee> employees,
            Map<Integer, String> header,
            Row row) {
        Extraction extraction = new Extraction();
        Waste waste = new Waste();
        int columnIndex = 1;
        Iterable<Cell> cells = row::cellIterator;
        for (Cell cell : cells) {
            String columnName = header.get(columnIndex);
            if (columnName == null) {
                continue;
            }
            switch (columnName) {
                case  "Lot" -> {
                    String stringCellValue = dataFormatter.formatCellValue(cell);
                    Material material = materialService.getMaterial(stringCellValue);
                    extraction.setMaterial(material);
                }
                case "Name" -> {
                    Material material = extraction.getMaterial();
                    material.setName(dataFormatter.formatCellValue(cell));
                }
                case "Made on" -> {
                    Date dateCellValue = cell.getDateCellValue();
                    extraction.setPreparedOn(dateCellValue);
                }
                case "Weight before" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    extraction.setWeightBefore(numericCellValue);
                }
                case "Weight after" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    extraction.setWeightAfter(numericCellValue);
                }
                case "Loss kg" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    waste.setLossAfterExtractionInKg(numericCellValue);
                    extraction.setWaste(waste);
                }
                case "Loss %" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    waste.setLossAfterExtractionInPercent(numericCellValue);
                    extraction.setWaste(waste);
                }
                case "Prepared by" -> {
                    String stringCellValue = cell.getStringCellValue();
                    Employee employee = new Employee();
                    employee.setName(stringCellValue);
                    extraction.setRealizedBy(employee);
                }
                case "Received back" -> {
                    Date dateCellValue = cell.getDateCellValue();
                    extraction.setReceivedBackOn(dateCellValue);
                }
                case "Result of the tested sample" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    extraction.setSampleTestResult(numericCellValue);
                }
                case "Packed kg" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    waste.setPackedKg(numericCellValue);
                    extraction.setWaste(waste);
                }
                case "Aggregate loss kg" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    waste.setLossTotalKg(numericCellValue);
                    extraction.setWaste(waste);
                }
                case "Aggregate loss %" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    waste.setLossTotalPercents(numericCellValue);
                    extraction.setWaste(waste);
                }
                case "Purchase price EUR" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    PurchasePrice purchasePrice = new PurchasePrice();
                    //TODO Set currency
                    Currency currency = new Currency();
                    currency.setCurrency("EUR");
                    purchasePrice.setCurrency(currency);
                    purchasePrice.setPurchasePrice(BigDecimal.valueOf(numericCellValue));
                    extraction.setPurchasePrice(purchasePrice);

                }
                case "Purchase price CHF" -> {
                    double numericCellValue = cell.getNumericCellValue();
                    PurchasePrice purchasePrice = new PurchasePrice();
                    //TODO Set currency
                    Currency currency = new Currency();
                    currency.setCurrency("CHF");
                    purchasePrice.setCurrency(currency);
                    purchasePrice.setPurchasePrice(BigDecimal.valueOf(numericCellValue));
                    extraction.setPurchasePrice(purchasePrice);
                }
                case "Sale price 10% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("10% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 20% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("20% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 30% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("30% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 40% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("40% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 50% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("50% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 60% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("60% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 70% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("70% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
                case "Sale price 100% marge" -> {
                    BigDecimal numericCellValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    SalePrice salePrice = new SalePrice();
                    Margin margin = getMargin("100% marge");
                    salePrice.setMargin(margin);
                    salePrice.setSalePrice(numericCellValue);
                }
            }
            columnIndex++;
        }
        return extraction;
    }

    private Margin getMargin(String name) {
        return marginService.getByName(name);
    }


    private static Serializable getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> cell.getNumericCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case FORMULA -> cell.getNumericCellValue();
            default -> "None or black or error";
        };
    }
}
