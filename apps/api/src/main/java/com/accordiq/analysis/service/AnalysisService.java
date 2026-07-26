package com.accordiq.analysis.service;

import com.accordiq.analysis.dto.AnalysisResponse;

import java.util.UUID;

public interface AnalysisService {

    AnalysisResponse getAnalysis(UUID documentId);

    void deleteAnalysis(UUID documentId);

}