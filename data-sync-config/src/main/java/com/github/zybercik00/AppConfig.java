package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.repository.proces.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MaterialService materialService(MaterialRepo materialRepo) {
        return new MaterialService(materialRepo);
    }

    @Bean
    public EmployeeService employeeService(EmployeeRepo employeeRepo) {

        return new EmployeeService(employeeRepo);
    }

    @Bean
    public MarginService marginService(MarginRepo marginRepo) {
        return new MarginService(marginRepo);
    }

    @Bean
    public CurrencyService currencyService(CurrencyRepo currencyRepo) {
        return new CurrencyService(currencyRepo);
    }

    @Bean
    public PurchasePriceService purchasePriceService(CurrencyService currencyService) {
        return new PurchasePriceService(currencyService);
    }

    @Bean
    public SalePriceService salePriceService(MarginService marginService) {
        return new SalePriceService(marginService);
    }

    @Bean
    public ExcelTableFactory excelTableFactory(DataFormatter dataFormatter) {
        return new ExcelTableFactory(dataFormatter);
    }

    @ConfigurationProperties(prefix = "com.github.zybercik00")
    @Bean
    public ExcelSheetProperties excelSheetProperties() {
        return new ExcelSheetProperties();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ExtractionService extractionService(
            ExtractionRepo extractionRepo,
            WasteRepo wasteRepo,
            MaterialService materialService,
            EmployeeService employeeService,
            PurchasePriceService purchasePriceService,
            SalePriceService salePriceService,
            ExcelTableFactory excelTableFactory) {
        return new ExtractionService(
                extractionRepo,
                materialService,
                employeeService,
                purchasePriceService,
                salePriceService,
                excelTableFactory);
    }

    @Bean(initMethod = "initService")
    public ExcelFileReader excelFileReader(
            ExtractionService extractionService,
            ExcelSheetProperties excelSheetProperties,
            ObjectMapper objectMapper) {
        return new ExcelFileReader(
                extractionService,
                excelSheetProperties,
                objectMapper);
    }

    @Bean
    public DataFormatter poiDataFormatter() {

        return new DataFormatter();
    }

}