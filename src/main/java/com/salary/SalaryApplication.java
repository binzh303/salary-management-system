package com.salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SalaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalaryApplication.class, args);
    }
}