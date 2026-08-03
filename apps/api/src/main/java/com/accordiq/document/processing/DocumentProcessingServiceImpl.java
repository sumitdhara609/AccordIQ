package com.accordiq.document.processing;

import com.accordiq.ai.dto.request.DocumentAnalysisRequest;
import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.ai.dto.response.ExtractedField;
import com.accordiq.ai.service.AIService;
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
    private final AIService aiService;

    public DocumentProcessingServiceImpl(
            OCRService ocrService,
            AIService aiService
    ) {
        this.ocrService = ocrService;
        this.aiService = aiService;
    }

    @Override
    public void process(Document document) {

        LOGGER.info(
                "Processing started for document {}",
                document.getId()
        );

        OCRResult ocrResult = ocrService.extractText(
                Path.of(document.getStoragePath())
        );

        LOGGER.info(
                "OCR completed for document {}",
                document.getId()
        );

        LOGGER.info(
                "OCR Confidence: {}",
                ocrResult.getConfidence()
        );

        LOGGER.info(
                "OCR Processing Time: {} ms",
                ocrResult.getProcessingTimeMillis()
        );

        LOGGER.debug(
                "Extracted Text:{}{}",
                System.lineSeparator(),
                ocrResult.getExtractedText()
        );

        DocumentAnalysisRequest request =
                DocumentAnalysisRequest.builder()
                        .documentId(document.getId().toString())
                        .documentType("UNKNOWN")
                        .extractedText(
                                ocrResult.getExtractedText()
                        )
                        .build();

        LOGGER.info(
                "Sending document {} to Gemini.",
                document.getId()
        );

        DocumentAnalysisResponse response =
                aiService.analyzeDocument(request);

        LOGGER.info(
                "AI document analysis completed."
        );

        LOGGER.info(
                "Detected document type: {}",
                response.getDocumentType()
        );

        LOGGER.info(
                "Summary: {}",
                response.getSummary()
        );

        if (response.getFields() != null) {

            LOGGER.info(
                    "Extracted {} fields.",
                    response.getFields().size()
            );

            for (ExtractedField field : response.getFields()) {

                LOGGER.info(
                        "{} = {} (confidence={})",
                        field.getName(),
                        field.getValue(),
                        field.getConfidence()
                );
            }
        }

        /*
         * Feature 019
         *
         * Persist AI analysis.
         * Persist extracted fields.
         * Update document status.
         * Enable search indexing.
         */

    }
}