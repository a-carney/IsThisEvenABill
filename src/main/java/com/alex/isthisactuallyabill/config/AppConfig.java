package com.alex.isthisactuallyabill.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiProperties.class)
public class AppConfig {
    // Beans and additional configurations if needed
}