package com.accordiq.ocr.service;

import java.io.File;

public interface OcrService {

    /**
     * Extract text from an image or PDF.
     *
     * @param file uploaded document
     * @return extracted text
     */
    String extractText(File file);

}