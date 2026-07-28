package com.accordiq.ocr.service;

import java.nio.file.Path;

public interface OcrService {

    String extractText(Path file);

}