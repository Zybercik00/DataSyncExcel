package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.zybercik00.repository.process.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final EntityManager entityManager;
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
    public ObjectWriter objectWriter(ObjectMapper objectMapper) {
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Bean
    public MappingService mappingService() {
        return new MappingService(entityManager);
    }

    @Bean
    public MappingAttributeService mappingAttributeService() {
        return new MappingAttributeService();
    }

    @Bean
    public ExtractionMappingService extractionMappingService() {
        return new ExtractionMappingService();
    }

    @Bean
    public ExtractionService extractionService(
            ExtractionRepo extractionRepo,
            MappingService mappingService,
            ExcelTableFactory excelTableFactory,
            MappingAttributeService attributeService,
            ExtractionMappingService extractionMappingService) {
        return new ExtractionService(
                extractionRepo,
                mappingService,
                excelTableFactory,
                attributeService,
                extractionMappingService);
    }

    @Bean(initMethod = "initService")
    public ExcelFileReader excelFileReader(
            ExtractionService extractionService,
            ExcelSheetProperties excelSheetProperties,
            ObjectWriter objectWriter) {
        return new ExcelFileReader(
                extractionService,
                excelSheetProperties,
                objectWriter);
    }

    @Bean
    public DataFormatter poiDataFormatter() {
        return new DataFormatter();
    }


}