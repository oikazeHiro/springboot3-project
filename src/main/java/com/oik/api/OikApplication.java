package com.oik.api;

import com.oik.api.utils.jwt.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.oik.api.mapper")
@EnableConfigurationProperties({RsaKeyProperties.class})
public class OikApplication {

    public static void main(String[] args) {
        SpringApplication.run(OikApplication.class, args);
    }

}
