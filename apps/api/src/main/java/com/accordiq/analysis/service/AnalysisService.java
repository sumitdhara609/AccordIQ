package com.accordiq.analysis.service;

import com.accordiq.analysis.dto.request.AnalyzeTextRequest;
import com.accordiq.analysis.dto.response.AnalyzeResponse;

public interface AnalysisService {

    AnalyzeResponse analyzeText(
            AnalyzeTextRequest request
    );
}