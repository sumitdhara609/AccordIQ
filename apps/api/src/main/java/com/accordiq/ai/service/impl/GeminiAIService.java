package com.accordiq.ai.service.impl;

import com.accordiq.ai.config.AIProperties;
import com.accordiq.ai.dto.AIAnalysis;
import com.accordiq.ai.dto.AIResponse;
import com.accordiq.ai.service.AIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiAIService implements AIService {

    private final Client client;
    private final AIProperties properties;
    private final ObjectMapper objectMapper;

    public GeminiAIService(AIProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;

        this.client = Client.builder()
                .apiKey(properties.apiKey())
                .build();
    }

    @Override
    public AIResponse extract(String ocrText) {

        String prompt = """
                You are an enterprise document intelligence engine.

                Analyze the OCR text and return ONLY valid JSON.

                Rules:
                - Do not include markdown.
                - Do not include ```json fences.
                - Do not include explanations.
                - Return only a valid JSON object.

                JSON Schema:

                {
                  "documentType": "",
                  "summary": "",
                  "entities": {
                    "people": [],
                    "organizations": [],
                    "dates": [],
                    "amounts": []
                  },
                  "keyPoints": []
                }

                OCR Text:

                %s
                """.formatted(ocrText);

        GenerateContentResponse response =
                client.models.generateContent(
                        properties.model(),
                        prompt,
                        null
                );

        try {

            AIAnalysis analysis =
                    objectMapper.readValue(
                            response.text(),
                            AIAnalysis.class
                    );

            return new AIResponse(analysis);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response.", e);
        }
    }
}