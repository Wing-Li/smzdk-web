package com.lyl.smzdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SmzdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmzdkApplication.class, args);
    }
}
