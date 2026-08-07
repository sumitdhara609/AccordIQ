package com.accordiq.documentanalysis.service;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;

public interface DocumentAnalysisService {

    DocumentAnalysis saveAnalysis(
            Document document,
            DocumentAnalysisResponse response
    );

}