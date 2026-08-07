package com.accordiq.documentfield.service;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import com.accordiq.documentfield.dto.request.UpdateFieldRequest;
import com.accordiq.documentfield.dto.response.DocumentFieldResponse;

import java.util.UUID;

public interface DocumentFieldService {

    void saveFields(
            DocumentAnalysis analysis,
            DocumentAnalysisResponse response
    );

    DocumentFieldResponse updateField(
            UUID fieldId,
            UpdateFieldRequest request
    );

}