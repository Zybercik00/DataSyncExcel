package com.github.zybercik00;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExcelTableWithHeader {
    private final XSSFSheet sheet;
    private final DataFormatter dataFormatter;
    private final Map<String, Integer> header = new HashMap<>();

    public ExcelTableWithHeader(XSSFSheet sheet, DataFormatter dataFormatter) {
        this.sheet = sheet;
        this.dataFormatter = dataFormatter;
        fillHeader();
    }

    private void fillHeader() {
        int columnIndex = 1;
        Iterable<Cell> cells = sheet.getRow(sheet.getFirstRowNum())::cellIterator;
        for (Cell cell : cells) {
            String columnName = dataFormatter.formatCellValue(cell).trim();
            header.put(columnName, columnIndex);
            columnIndex++;
        }
    }

    public Cursor cursor() {
        return new Cursor();
    }

    public class Cursor {
        private int rowIndex;
        private XSSFRow row;

        public Cursor() {
            rowIndex = sheet.getFirstRowNum() + 1;
            // TODO There could be empty table, only consisting of header
            row = sheet.getRow(rowIndex);
        }

        public boolean next() {
            if ( rowIndex >= sheet.getLastRowNum() ) {
                return false;
            }
            rowIndex++;
            row = sheet.getRow(rowIndex);
            return true;
        }

        public BigDecimal getNumericValue(String column) {
            Integer cellNum = Objects.requireNonNull(header.get(column), column);
            Cell cell = row.getCell(cellNum);
            return BigDecimal.valueOf(cell.getNumericCellValue());
        }

        public Date getDateValue(String column) {
            Cell cell = row.getCell(header.get(column));
            return cell.getDateCellValue();
        }

        public String getStringValue(String column) {
            Cell cell = row.getCell(header.get(column));
            return dataFormatter.formatCellValue(cell);
        }
    }
}