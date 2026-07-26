package com.accordiq.config.ocr;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ocr.tesseract")
public record OCRProperties(
        String dataPath,
        String language,
        Integer engineMode,
        Integer pageSegmentationMode
) {
}