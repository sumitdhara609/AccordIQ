package com.accordiq.document.processing;

import com.accordiq.document.entity.Document;
import com.accordiq.ocr.service.OcrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class DocumentProcessingServiceImpl
        implements DocumentProcessingService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DocumentProcessingServiceImpl.class);

    private final OcrService ocrService;

    public DocumentProcessingServiceImpl(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @Override
    public void process(Document document) {

        LOGGER.info(
                "Processing started for document {}",
                document.getId()
        );

        String extractedText = ocrService.extractText(
                Path.of(document.getStoragePath())
        );

        LOGGER.info(
                "OCR Result: {}",
                extractedText
        );
    }
}