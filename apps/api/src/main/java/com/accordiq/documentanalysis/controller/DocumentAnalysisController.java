package com.accordiq.documentanalysis.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.documentanalysis.dto.response.DocumentAnalysisDetailResponse;
import com.accordiq.documentanalysis.service.DocumentAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/document-analyses")
public class DocumentAnalysisController {

    private final DocumentAnalysisService documentAnalysisService;

    public DocumentAnalysisController(
            DocumentAnalysisService documentAnalysisService
    ) {
        this.documentAnalysisService = documentAnalysisService;
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<ApiResponse<DocumentAnalysisDetailResponse>> getAnalysis(
            @PathVariable UUID documentId
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document analysis retrieved successfully.",
                        documentAnalysisService.getAnalysis(documentId)
                )
        );
    }
}