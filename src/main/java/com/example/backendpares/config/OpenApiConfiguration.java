package com.example.backendpares.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
            title = "POKÉDEX - API",
            version = "v0.1",
            description = "All Pokemon in one API"
    )
)

@Configuration
public class OpenApiConfiguration {
}
