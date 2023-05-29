package com.oik.api.config.security.Customizer;

import com.oik.api.config.security.exception.SecurityAuthenticationEntryPoint;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/29 16:45
 */
@Component
public class ExceptionHandlingConfigurerCustomizer implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {
    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer) {
        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
    }
}
