package com.oik.api.config.security.Customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/29 17:11
 */
@Component
public class CsrfConfigurerCustomizer implements Customizer<CsrfConfigurer<HttpSecurity>> {
    @Override
    public void customize(CsrfConfigurer csrfConfigurer) {
        csrfConfigurer.disable();
    }
}
