package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author bryancocha@gmail.com
 * @class_name WebMvcConfigurer.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022.
 */

@EnableConfigurationProperties
@RequiredArgsConstructor
@SpringBootConfiguration
public class SpringDocConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("*");
            }
        };
    }
}
