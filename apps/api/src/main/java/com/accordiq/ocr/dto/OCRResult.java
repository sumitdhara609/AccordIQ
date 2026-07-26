package com.accordiq.ocr.dto;

import com.accordiq.ocr.enums.OCREngine;

public record OCRResult(
        String extractedText,
        Long processingTimeMs,
        OCREngine engine
) {
}