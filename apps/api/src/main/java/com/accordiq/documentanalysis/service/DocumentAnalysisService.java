package com.accordiq.documentanalysis.service;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.documentanalysis.dto.response.DocumentAnalysisDetailResponse;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;

import java.util.UUID;

public interface DocumentAnalysisService {

    DocumentAnalysis saveAnalysis(
            Document document,
            DocumentAnalysisResponse response
    );

    DocumentAnalysisDetailResponse getAnalysis(
            UUID documentId
    );
}