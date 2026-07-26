package com.accordiq.analysis.application;

import com.accordiq.ocr.dto.response.OCRResponse;

import java.io.IOException;
import java.util.UUID;

public interface DocumentAnalysisApplicationService {

    OCRResponse analyzeDocument(UUID documentId) throws IOException;

}