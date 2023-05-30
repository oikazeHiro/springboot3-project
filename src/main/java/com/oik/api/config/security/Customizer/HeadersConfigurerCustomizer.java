package com.oik.api.config.security.Customizer;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/29 16:52
 */
@Component
public class HeadersConfigurerCustomizer implements Customizer<HeadersConfigurer<HttpSecurity>> {
    @Override
    public void customize(HeadersConfigurer<HttpSecurity> httpSecurityHeadersConfigurer) {
        httpSecurityHeadersConfigurer.frameOptions(new Customizer<HeadersConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.FrameOptionsConfig>() {
            @Override
            public void customize(HeadersConfigurer<HttpSecurity>.FrameOptionsConfig frameOptionsConfig) {
//                frameOptionsConfig.disable();
            }
        }).disable();
    }
}
