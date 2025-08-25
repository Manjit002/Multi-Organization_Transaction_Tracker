package com.mjt.Multi_Organization_Transaction_Tracker.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper mapper(){

        return new ModelMapper();
    }
}
