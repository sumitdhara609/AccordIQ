package com.accordiq.ocr.dto.response;

import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.ocr.enums.OCREngine;

import java.util.UUID;

public record OCRResponse(
        UUID documentId,
        String extractedText,
        Double confidence,
        Long processingTimeMs,
        OCREngine engine,
        DocumentStatus status
) {
}