package com.accordiq.analysis.controller;

import com.accordiq.analysis.dto.AnalysisResponse;
import com.accordiq.analysis.service.AnalysisService;
import com.accordiq.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analysis")
@Tag(name = "Analysis", description = "Document Analysis APIs")
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/{documentId}")
    @Operation(summary = "Get analysis by document ID")
    public ResponseEntity<ApiResponse<AnalysisResponse>> getAnalysis(
            @PathVariable UUID documentId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Analysis retrieved successfully.",
                        analysisService.getAnalysis(documentId)
                )
        );
    }

    @DeleteMapping("/{documentId}")
    @Operation(summary = "Delete analysis by document ID")
    public ResponseEntity<ApiResponse<Void>> deleteAnalysis(
            @PathVariable UUID documentId
    ) {

        analysisService.deleteAnalysis(documentId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Analysis deleted successfully.",
                        null
                )
        );
    }
}