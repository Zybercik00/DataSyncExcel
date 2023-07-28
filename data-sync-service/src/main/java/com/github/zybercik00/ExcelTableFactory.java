package com.github.zybercik00;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;

@RequiredArgsConstructor
public class ExcelTableFactory {
    private final DataFormatter dataFormatter;

    public ExcelTableWithHeader getExcelTable(XSSFSheet sheet) {
        return new ExcelTableWithHeader(sheet, dataFormatter);
    }
}