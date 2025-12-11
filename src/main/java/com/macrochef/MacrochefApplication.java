package com.macrochef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.macrochef")
public class MacrochefApplication {

    public static void main(String[] args) {
        SpringApplication.run(MacrochefApplication.class, args);
    }
}
