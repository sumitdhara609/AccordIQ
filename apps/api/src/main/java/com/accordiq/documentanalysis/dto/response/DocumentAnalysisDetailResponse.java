package com.accordiq.documentanalysis.dto.response;

import com.accordiq.documentfield.dto.response.DocumentFieldResponse;

import java.util.List;
import java.util.UUID;

public record DocumentAnalysisDetailResponse(

        UUID analysisId,

        UUID documentId,

        String documentType,

        String summary,

        Double confidence,

        List<DocumentFieldResponse> fields

) {
}