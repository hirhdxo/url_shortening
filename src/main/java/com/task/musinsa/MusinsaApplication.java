package com.task.musinsa;

import com.task.musinsa.properties.WebProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(WebProperties.class)
public class MusinsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusinsaApplication.class, args);
    }

}
