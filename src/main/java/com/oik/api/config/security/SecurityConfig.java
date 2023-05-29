package com.oik.api.config.security;

import com.oik.api.config.security.exception.SecurityAuthenticationEntryPoint;
import com.oik.api.config.security.mobile.MobileAuthenticationProvider;
import com.oik.api.config.security.mobile.MobileUserDetailsService;
import com.oik.api.config.security.mobile.MobileVerifyCodeService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 10:00
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final OncePerRequestFilter authenticationTokenFilter;
    private final PermitResource permitResource;
    private final UserDetailsService userDetailsService;
    private final MobileUserDetailsService mobileUserDetailsService;
    private final MobileVerifyCodeService mobileVerifyCodeService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    MobileAuthenticationProvider mobileAuthenticationProvider() {
        return new MobileAuthenticationProvider(mobileUserDetailsService, mobileVerifyCodeService);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<>();
        providerList.add(daoAuthenticationProvider());
        providerList.add(mobileAuthenticationProvider());

        ProviderManager providerManager = new ProviderManager(providerList);
        providerManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));

        return providerManager;
    }

    @Resource
    Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementConfigurerCustomizer;
    @Resource
    Customizer<ExceptionHandlingConfigurer<HttpSecurity>> exceptionHandlingConfigurerCustomizer;
    @Resource
    Customizer<HeadersConfigurer<HttpSecurity>> headersConfigurerCustomizer;
    @Resource
    Customizer<CsrfConfigurer<HttpSecurity>> csrfConfigurerCustomizer;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 忽略授权的地址列表
        List<String> permitList = permitResource.getPermitList();
        String[] permits = permitList.toArray(new String[0]);
        //= new Customizer<SessionManagementConfigurer<HttpSecurity>>();
//        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        exceptionHandlingConfigurer.authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
//        customizer.customize(sessionManagementConfigurer);
        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagementConfigurerCustomizer)
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(permits).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandlingConfigurerCustomizer)
//                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .headers(headersConfigurerCustomizer)
                .csrf(csrfConfigurerCustomizer)
        ;

        return http.build();
    }
}
