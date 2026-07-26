package com.accordiq.common.mapper;

import com.accordiq.ai.dto.AIAnalysis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonMapper() {
    }

    public static AIAnalysis toAnalysis(String json) {

        try {
            return OBJECT_MAPPER.readValue(json, AIAnalysis.class);

        } catch (JsonProcessingException e) {

            throw new RuntimeException("Failed to deserialize AI analysis.", e);

        }

    }

}