package com.accordiq.analysis.service;

import com.accordiq.ai.dto.request.DocumentAnalysisRequest;
import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.ai.service.GeminiService;
import com.accordiq.analysis.dto.request.AnalyzeTextRequest;
import com.accordiq.analysis.dto.response.AnalyzeResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final GeminiService geminiService;

    public AnalysisServiceImpl(
            GeminiService geminiService
    ) {
        this.geminiService = geminiService;
    }

    @Override
    public AnalyzeResponse analyzeText(
            AnalyzeTextRequest request
    ) {

        DocumentAnalysisRequest aiRequest =
                DocumentAnalysisRequest.builder()
                        .documentId("temporary")
                        .documentType("TEXT")
                        .extractedText(request.getText())
                        .build();

        DocumentAnalysisResponse aiResponse =
                geminiService.analyzeDocument(aiRequest);

        return new AnalyzeResponse(
                aiResponse.getSummary(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}