package com.accordiq.ocr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OCRResult {

    private String extractedText;

    private double confidence;

    private long processingTimeMillis;

}