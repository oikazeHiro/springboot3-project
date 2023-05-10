package com.oik.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.oik.api.mapper")
public class OikApplication {

    public static void main(String[] args) {
        SpringApplication.run(OikApplication.class, args);
    }

}
