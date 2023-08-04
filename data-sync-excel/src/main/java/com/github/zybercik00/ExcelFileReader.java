package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.zybercik00.entity.process.Extraction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
public class ExcelFileReader implements CommandLineRunner {

    private final ExtractionService extractionService;
    private final ExcelSheetProperties properties;
    private final ObjectWriter objectWriter;
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

    @Transactional
    void readListExcelFile() throws IOException {
        List<Extraction> extractions = getExtractions();
        processExtractions(extractions);
    }

    List<Extraction> getExtractions() throws IOException {
        List<Extraction> extractions;
        try (InputStream inputStream = excelResourceUrl.openStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(properties.getSheetName());
            extractions = extractionService.getExtractions(sheet);
        }
        return extractions;
    }

    private void processExtractions(List<Extraction> extractions) {
        extractions.stream()
                .map(this::toJson)
                .forEach(System.out::println);
    }

    private String toJson(Extraction extraction) {
        try {
            return objectWriter.writeValueAsString(extraction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}