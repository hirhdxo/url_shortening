package com.task.musinsa.config;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    UrlValidator urlValidator() {
        UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https", ""});

        return urlValidator;
    }
}
