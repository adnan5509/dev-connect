package com.aab.dev_connect.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI insuranceClaimsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DevConnect – A Developer Collaboration Platform")
                        .description("API documentation for the DevConnect – A Developer Collaboration Platform")
                        .version("v0.0.1"));
    }
}
