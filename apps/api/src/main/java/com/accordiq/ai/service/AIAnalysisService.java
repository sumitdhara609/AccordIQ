package com.accordiq.ai.service;

import com.accordiq.ai.dto.AIAnalysis;
import com.accordiq.ai.entity.AIAnalysisEntity;
import com.accordiq.document.entity.Document;

import java.util.Optional;
import java.util.UUID;

public interface AIAnalysisService {

    AIAnalysisEntity save(
            Document document,
            String ocrText,
            AIAnalysis analysis,
            String modelName,
            Long processingTimeMs
    );

    Optional<AIAnalysisEntity> findByDocumentId(UUID documentId);

    boolean exists(UUID documentId);

}