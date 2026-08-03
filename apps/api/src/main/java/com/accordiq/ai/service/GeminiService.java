package com.accordiq.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.accordiq.ai.config.GeminiConfiguration;
import com.accordiq.ai.exception.GeminiException;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService implements AIService {

    private static final Logger log = LoggerFactory.getLogger(GeminiService.class);

    private final Client client;
    private final GeminiConfiguration configuration;

    public GeminiService(GeminiConfiguration configuration) {

        this.configuration = configuration;

        try {

            this.client = Client.builder()
                    .apiKey(configuration.getApiKey())
                    .build();

            log.info("Gemini client initialized successfully.");

        } catch (Exception ex) {

            throw new GeminiException(
                    "Failed to initialize Gemini client.",
                    ex
            );
        }
    }

    @Override
    public String generateContent(String prompt) {

        try {

            log.info("Sending prompt to Gemini...");
            log.info("Model: {}", configuration.getModel());

            GenerateContentResponse response =
                    client.models.generateContent(
                            configuration.getModel(),
                            prompt,
                            null
                    );

            if (response == null) {
                throw new GeminiException("Gemini returned null response.");
            }

            String text = response.text();

            if (text == null || text.isBlank()) {
                throw new GeminiException("Gemini returned an empty response.");
            }

            return text;

        } catch (Exception ex) {

            log.error("Gemini API call failed.", ex);

            throw new GeminiException(
                    "Failed to generate content from Gemini: "
                            + ex.getClass().getSimpleName()
                            + " - "
                            + ex.getMessage(),
                    ex
            );
        }
    }

}