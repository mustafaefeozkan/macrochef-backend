package com.macrochef;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MacrochefApplication {

    public static void main(String[] args) {
        SpringApplication.run(MacrochefApplication.class, args);
    }


    @Bean
    CommandLineRunner logDatabaseUrl(Environment env) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("DEBUG - DB_URL: " + env.getProperty("DB_URL"));
            System.out.println("DEBUG - DATASOURCE URL: " + env.getProperty("spring.datasource.url"));
            System.out.println("=========================================");
        };
    }
}