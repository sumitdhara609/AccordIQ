package com.accordiq.document.processing;

import com.accordiq.ai.dto.request.DocumentAnalysisRequest;
import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.ai.dto.response.ExtractedField;
import com.accordiq.ai.service.AIService;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import com.accordiq.documentanalysis.service.DocumentAnalysisService;
import com.accordiq.documentfield.service.DocumentFieldService;
import com.accordiq.ocr.model.OCRResult;
import com.accordiq.ocr.service.OCRService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;

@Service
public class DocumentProcessingServiceImpl
        implements DocumentProcessingService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DocumentProcessingServiceImpl.class);

    private final OCRService ocrService;
    private final AIService aiService;
    private final DocumentAnalysisService documentAnalysisService;
    private final DocumentFieldService documentFieldService;

    public DocumentProcessingServiceImpl(
            OCRService ocrService,
            AIService aiService,
            DocumentAnalysisService documentAnalysisService,
            DocumentFieldService documentFieldService
    ) {
        this.ocrService = ocrService;
        this.aiService = aiService;
        this.documentAnalysisService = documentAnalysisService;
        this.documentFieldService = documentFieldService;
    }

    @Override
    @Transactional
    public DocumentAnalysisResponse process(Document document) {

        LOGGER.info(
                "Processing started for document {}",
                document.getId()
        );

        document.setStatus(DocumentStatus.PROCESSING);

        try {
            OCRResult ocrResult =
                    ocrService.extractText(
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

            document.setStatus(
                    DocumentStatus.OCR_COMPLETED
            );

            DocumentAnalysisRequest request =
                    DocumentAnalysisRequest.builder()
                            .documentId(
                                    document.getId().toString()
                            )
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

                for (ExtractedField field :
                        response.getFields()) {

                    LOGGER.info(
                            "{} = {} (confidence={})",
                            field.getName(),
                            field.getValue(),
                            field.getConfidence()
                    );
                }
            }

            DocumentAnalysis analysis =
                    documentAnalysisService.saveAnalysis(
                            document,
                            response
                    );

            documentFieldService.saveFields(
                    analysis,
                    response
            );

            document.setStatus(
                    DocumentStatus.REVIEW_REQUIRED
            );

            LOGGER.info(
                    "Document {} is ready for review.",
                    document.getId()
            );

            return response;

        } catch (Exception exception) {

            LOGGER.error(
                    "Document processing failed for document {}",
                    document.getId(),
                    exception
            );

            document.setStatus(
                    DocumentStatus.FAILED
            );

            throw exception;
        }
    }
}