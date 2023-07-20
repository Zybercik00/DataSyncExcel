package com.github.zybercik00;

import com.github.zybercik00.domain.proces.Employee;
import com.github.zybercik00.domain.proces.Extraction;
import com.github.zybercik00.domain.proces.Margin;
import com.github.zybercik00.domain.proces.Waste;
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

    private final MaterialService mAterialService;
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
        Extraction washProcess = new Extraction();
        Waste waste = new Waste();
        int columnIndex = 1;
        Iterable<Cell> cells = row::cellIterator;
        for (Cell cell : cells) {
            String columnName = header.get(columnIndex);
            if (columnName == null) {
                continue;
            }
            switch (columnName) {
                case  "1" -> {
                    //TODO
                }

            }
            columnIndex++;
        }
        return washProcess;
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
