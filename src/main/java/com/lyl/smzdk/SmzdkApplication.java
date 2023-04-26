package com.lyl.smzdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

// 常用注解解释：https://blog.csdn.net/sulia1234567890/article/details/122298586

@SpringBootApplication
@ServletComponentScan
@EnableJpaAuditing
public class SmzdkApplication {

    @PostConstruct
    void setDefaultTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SmzdkApplication.class, args);
    }
}
