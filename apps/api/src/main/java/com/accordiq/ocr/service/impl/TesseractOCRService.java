package com.accordiq.ocr.service.impl;

import com.accordiq.config.ocr.OCRProperties;
import com.accordiq.document.entity.Document;
import com.accordiq.ocr.dto.OCRResult;
import com.accordiq.ocr.enums.OCREngine;
import com.accordiq.ocr.service.OCRService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class TesseractOCRService implements OCRService {

    private final OCRProperties ocrProperties;

    public TesseractOCRService(OCRProperties ocrProperties) {
        this.ocrProperties = ocrProperties;
    }

    @Override
    public OCRResult extract(Document document) throws IOException {

        File file = new File(document.getStoragePath());

        if (!file.exists()) {
            throw new IOException(
                    "Stored file not found: " + file.getAbsolutePath()
            );
        }

        ITesseract tesseract = new Tesseract();

        tesseract.setDatapath(ocrProperties.dataPath());
        tesseract.setLanguage(ocrProperties.language());
        tesseract.setOcrEngineMode(ocrProperties.engineMode());
        tesseract.setPageSegMode(ocrProperties.pageSegmentationMode());

        long startTime = System.currentTimeMillis();

        try {

            String extractedText = tesseract.doOCR(file);

            long processingTime =
                    System.currentTimeMillis() - startTime;

            return new OCRResult(
                    extractedText,
                    processingTime,
                    OCREngine.TESSERACT
            );

        } catch (TesseractException ex) {

            throw new IOException(
                    "OCR processing failed.",
                    ex
            );
        }
    }
}