package com.github.zybercik00;

import com.github.zybercik00.repository.proces.EmployeeRepo;
import com.github.zybercik00.repository.proces.MarginRepo;
import com.github.zybercik00.repository.proces.MaterialRepo;
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

    @ConfigurationProperties(prefix = "com.github.zybercik00")
    @Bean
    public ExcelSheetProperties excelSheetProperties() {

        return new ExcelSheetProperties();
    }

    @Bean(initMethod = "initService")
    public ExcelFileReader excelFileReader(
            MaterialService materialService,
            EmployeeService employeeService,
            MarginService marginService,
            ExcelSheetProperties excelSheetProperties,
            DataFormatter poiDataFormatter) {
        return new ExcelFileReader(
                materialService, employeeService, marginService,
                excelSheetProperties,
                poiDataFormatter);
    }

    @Bean
    public DataFormatter poiDataFormatter() {

        return new DataFormatter();
    }

}
