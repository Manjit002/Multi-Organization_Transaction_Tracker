package com.mjt.Multi_Organization_Transaction_Tracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class BeansConfig {
    @Value("${app.oauth2.google.jwkSetUri}")
    private String jwkSetUri;

    @Bean
    public JwtDecoder googleJwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }
}

