package com.accordiq.ocr.provider.tesseract;

import com.accordiq.ocr.exception.OCRException;
import com.accordiq.ocr.model.OCRResult;
import com.accordiq.ocr.service.OCRService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class TesseractOCRService implements OCRService {

    @Override
    public OCRResult extractText(Path filePath) {

        throw new OCRException(
                "Tesseract OCR integration is not implemented yet."
        );

        /*
        Future implementation:

        return OCRResult.builder()
                .extractedText(text)
                .confidence(confidence)
                .processingTimeMillis(time)
                .build();
        */
    }
}