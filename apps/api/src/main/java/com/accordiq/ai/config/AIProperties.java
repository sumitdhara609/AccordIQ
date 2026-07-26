package com.accordiq.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ai.gemini")
public record AIProperties(
        String apiKey,
        String model
) {
}