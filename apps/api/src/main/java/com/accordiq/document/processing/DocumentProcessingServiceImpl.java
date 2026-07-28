package com.accordiq.document.processing;

import com.accordiq.analysis.service.DocumentAnalysisService;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.ocr.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentProcessingServiceImpl
        implements DocumentProcessingService {

    private final OcrService ocrService;
    private final DocumentRepository documentRepository;
    private final DocumentAnalysisService documentAnalysisService;

    @Override
    public void process(Document document) {

        log.info(
                "Starting OCR processing for document {}",
                document.getId()
        );

        try {

            document.setStatus(DocumentStatus.PROCESSING);
            documentRepository.save(document);

            File file = new File(document.getStoragePath());

            String extractedText = ocrService.extractText(file);

            documentAnalysisService.saveOcrResult(
                    document,
                    extractedText,
                    "Tesseract OCR 5",
                    "eng"
            );

            document.setStatus(DocumentStatus.OCR_COMPLETED);
            documentRepository.save(document);

            log.info(
                    "OCR completed successfully for document {}",
                    document.getId()
            );

        } catch (Exception ex) {

            log.error(
                    "OCR failed for document {}",
                    document.getId(),
                    ex
            );

            document.setStatus(DocumentStatus.FAILED);
            documentRepository.save(document);

            throw new RuntimeException(
                    "Failed to process document.",
                    ex
            );
        }

    }

}