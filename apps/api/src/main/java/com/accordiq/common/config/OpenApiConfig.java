package com.accordiq.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI accordIQOpenAPI() {

        return new OpenAPI()

                .info(

                        new Info()

                                .title("AccordIQ API")

                                .description("""
Enterprise OCR & AI Document Intelligence Platform

Built with:

• Spring Boot
• PostgreSQL (Neon)
• Tesseract OCR
• Gemini AI
• Swagger
""")

                                .version("v1")

                                .contact(

                                        new Contact()

                                                .name("Sumit Dhara")
                                                .email("your-email@example.com")

                                )

                                .license(

                                        new License()

                                                .name("MIT")

                                )

                );

    }

}