package com.accordiq.analysis.mapper;

import com.accordiq.ai.dto.AIAnalysis;
import com.accordiq.ai.entity.AIAnalysisEntity;
import com.accordiq.analysis.dto.AnalysisResponse;
import com.accordiq.common.mapper.MapperConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfiguration.class)
public interface AnalysisMapper {

    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "originalFileName", source = "document.originalFileName")
    @Mapping(target = "analysis", source = "aiJson", qualifiedByName = "jsonToAnalysis")
    AnalysisResponse toResponse(AIAnalysisEntity entity);

    @Named("jsonToAnalysis")
    default AIAnalysis jsonToAnalysis(String json) {

        if (json == null || json.isBlank()) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, AIAnalysis.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to deserialize AI analysis JSON.", e);
        }
    }
}