package com.github.zybercik00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.github.zybercik00.domain")
@EnableJpaRepositories(basePackages = "com.github.zybercik00.repository")
@SpringBootApplication
public class DataSyncExcelApplication {
    public static void main(String[] args) {

        SpringApplication.run(DataSyncExcelApplication.class, args);
    }
}