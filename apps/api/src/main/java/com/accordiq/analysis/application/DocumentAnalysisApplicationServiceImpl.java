package com.accordiq.analysis.application;

import com.accordiq.ai.dto.AIAnalysis;
import com.accordiq.ai.service.AIAnalysisService;
import com.accordiq.ai.service.AIService;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.ocr.dto.OCRResult;
import com.accordiq.ocr.dto.response.OCRResponse;
import com.accordiq.ocr.service.OCRService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class DocumentAnalysisApplicationServiceImpl
        implements DocumentAnalysisApplicationService {

    private final DocumentRepository documentRepository;
    private final OCRService ocrService;
    private final AIService aiService;
    private final AIAnalysisService aiAnalysisService;

    public DocumentAnalysisApplicationServiceImpl(
            DocumentRepository documentRepository,
            OCRService ocrService,
            AIService aiService,
            AIAnalysisService aiAnalysisService
    ) {
        this.documentRepository = documentRepository;
        this.ocrService = ocrService;
        this.aiService = aiService;
        this.aiAnalysisService = aiAnalysisService;
    }

    @Override
    public OCRResponse analyzeDocument(UUID documentId) throws IOException {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Document not found: " + documentId
                        )
                );

        document.setStatus(DocumentStatus.PROCESSING);
        documentRepository.save(document);

        try {

            OCRResult ocrResult = ocrService.extract(document);

            AIAnalysis analysis =
                    aiService.extract(
                            ocrResult.extractedText()
                    ).analysis();

            aiAnalysisService.save(
                    document,
                    ocrResult.extractedText(),
                    analysis,
                    "Gemini",
                    ocrResult.processingTimeMs()
            );

            document.setStatus(DocumentStatus.OCR_COMPLETED);
            documentRepository.save(document);

            return new OCRResponse(
                    document.getId(),
                    ocrResult.extractedText(),
                    analysis,
                    null,
                    ocrResult.processingTimeMs(),
                    ocrResult.engine(),
                    document.getStatus()
            );

        } catch (Exception ex) {

            document.setStatus(DocumentStatus.FAILED);
            documentRepository.save(document);

            if (ex instanceof IOException ioException) {
                throw ioException;
            }

            throw new IOException(
                    "Document analysis failed.",
                    ex
            );
        }
    }
}