package com.accordiq.document.processing;

import com.accordiq.document.entity.Document;
import com.accordiq.ocr.model.OCRResult;
import com.accordiq.ocr.service.OCRService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class DocumentProcessingServiceImpl
        implements DocumentProcessingService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DocumentProcessingServiceImpl.class);

    private final OCRService ocrService;

    public DocumentProcessingServiceImpl(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @Override
    public void process(Document document) {

        LOGGER.info(
                "Processing started for document {}",
                document.getId()
        );

        OCRResult result = ocrService.extractText(
                Path.of(document.getStoragePath())
        );

        LOGGER.info(
                "OCR completed for document {}",
                document.getId()
        );

        LOGGER.info(
                "Extracted Text: {}",
                result.getExtractedText()
        );

        LOGGER.info(
                "Confidence: {}",
                result.getConfidence()
        );

        LOGGER.info(
                "Processing Time: {} ms",
                result.getProcessingTimeMillis()
        );
    }
}