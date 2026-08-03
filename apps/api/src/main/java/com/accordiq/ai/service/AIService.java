package com.accordiq.ai.service;

import com.accordiq.ai.dto.request.DocumentAnalysisRequest;
import com.accordiq.ai.dto.response.DocumentAnalysisResponse;

/**
 * Common abstraction for all AI providers used by AccordIQ.
 */
public interface AIService {

    /**
     * Generates raw text from the AI model.
     *
     * @param prompt Prompt sent to the AI model
     * @return Generated response
     */
    String generateContent(String prompt);

    /**
     * Analyses OCR extracted document text and returns
     * a structured response.
     *
     * @param request Document analysis request
     * @return Structured document analysis
     */
    DocumentAnalysisResponse analyzeDocument(
            DocumentAnalysisRequest request
    );

}