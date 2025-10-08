package com.platzi.platzi.market.web.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("market-api")
                .packagesToScan("com.platzi.platzi.market.web.controller")
                .build();
    }
}
