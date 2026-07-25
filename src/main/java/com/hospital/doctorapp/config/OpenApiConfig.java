package com.hospital.doctorapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Doctor App",
                version = "1.0",
                description = "API for managing tutorials",
                contact = @Contact(name = "API Support", email = "support@example.com")
        )
)
public class OpenApiConfig {
}
