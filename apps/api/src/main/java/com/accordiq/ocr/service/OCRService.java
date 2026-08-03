package com.accordiq.ocr.service;

import com.accordiq.ocr.model.OCRResult;

import java.nio.file.Path;

public interface OCRService {

    OCRResult extractText(Path filePath);

}