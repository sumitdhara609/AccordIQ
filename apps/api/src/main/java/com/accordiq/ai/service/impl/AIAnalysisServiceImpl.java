package com.accordiq.ai.service.impl;

import com.accordiq.ai.dto.AIAnalysis;
import com.accordiq.ai.entity.AIAnalysisEntity;
import com.accordiq.ai.repository.AIAnalysisRepository;
import com.accordiq.ai.service.AIAnalysisService;
import com.accordiq.document.entity.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AIAnalysisServiceImpl implements AIAnalysisService {

    private final AIAnalysisRepository repository;
    private final ObjectMapper objectMapper;

    public AIAnalysisServiceImpl(
            AIAnalysisRepository repository,
            ObjectMapper objectMapper
    ) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public AIAnalysisEntity save(
            Document document,
            String ocrText,
            AIAnalysis analysis,
            String modelName,
            Long processingTimeMs
    ) {

        try {

            String aiJson = objectMapper.writeValueAsString(analysis);

            AIAnalysisEntity entity = AIAnalysisEntity.builder()
                    .document(document)
                    .ocrText(ocrText)
                    .aiJson(aiJson)
                    .modelName(modelName)
                    .processingTimeMs(processingTimeMs)
                    .build();

            return repository.save(entity);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize AI analysis.", e);
        }
    }

    @Override
    public Optional<AIAnalysisEntity> findByDocumentId(UUID documentId) {
        return repository.findByDocument_Id(documentId);
    }

    @Override
    public boolean exists(UUID documentId) {
        return repository.existsByDocument_Id(documentId);
    }
}