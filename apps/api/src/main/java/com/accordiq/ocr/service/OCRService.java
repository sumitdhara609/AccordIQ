package com.accordiq.ocr.service;

import com.accordiq.ocr.dto.response.OCRResponse;

import java.io.IOException;
import java.util.UUID;

public interface OCRService {

    OCRResponse process(UUID documentId) throws IOException;

}