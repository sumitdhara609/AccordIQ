package com.accordiq.ocr.service;

import com.accordiq.document.entity.Document;
import com.accordiq.ocr.dto.OCRResult;

import java.io.IOException;

public interface OCRService {

    OCRResult extract(Document document) throws IOException;

}