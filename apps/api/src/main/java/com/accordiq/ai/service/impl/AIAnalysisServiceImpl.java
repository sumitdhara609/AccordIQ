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

            // Temporary debug logging
            System.out.println("========== AIAnalysis ==========");
            System.out.println(analysis);

            System.out.println("========== Serialized JSON ==========");
            System.out.println(aiJson);

            AIAnalysisEntity entity = AIAnalysisEntity.builder()
                    .document(document)
                    .ocrText(ocrText)
                    .aiJson(aiJson)
                    .modelName(modelName)
                    .processingTimeMs(processingTimeMs)
                    .build();

            AIAnalysisEntity savedEntity = repository.save(entity);

            System.out.println("========== Saved Entity ==========");
            System.out.println("ID          : " + savedEntity.getId());
            System.out.println("Document ID : " + savedEntity.getDocument().getId());
            System.out.println("AI JSON     : " + savedEntity.getAiJson());

            return savedEntity;

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