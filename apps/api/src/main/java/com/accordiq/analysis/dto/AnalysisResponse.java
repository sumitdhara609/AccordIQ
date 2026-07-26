package com.accordiq.analysis.dto;

import com.accordiq.ai.dto.AIAnalysis;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnalysisResponse(

        UUID documentId,

        String originalFileName,

        String ocrText,

        AIAnalysis analysis,

        String modelName,

        Long processingTimeMs,

        LocalDateTime createdAt

) {
}