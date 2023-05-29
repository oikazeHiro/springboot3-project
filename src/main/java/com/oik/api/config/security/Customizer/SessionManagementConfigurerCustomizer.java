package com.oik.api.config.security.Customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/29 16:40
 */
@Component
public class SessionManagementConfigurerCustomizer implements Customizer<SessionManagementConfigurer<HttpSecurity>> {
    @Override
    public void customize(SessionManagementConfigurer<HttpSecurity> httpSecuritySessionManagementConfigurer) {
        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
