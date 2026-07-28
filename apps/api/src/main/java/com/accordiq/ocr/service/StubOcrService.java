package com.accordiq.ocr.service;

import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class StubOcrService implements OcrService {

    @Override
    public String extractText(Path file) {
        return "OCR processing is not implemented yet.";
    }
}