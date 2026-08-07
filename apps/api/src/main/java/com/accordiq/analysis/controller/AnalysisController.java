package com.accordiq.analysis.controller;

import com.accordiq.analysis.dto.request.AnalyzeTextRequest;
import com.accordiq.analysis.dto.response.AnalyzeResponse;
import com.accordiq.analysis.service.AnalysisService;
import com.accordiq.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analyze")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(
            AnalysisService analysisService
    ) {
        this.analysisService = analysisService;
    }

    @PostMapping("/text")
    public ResponseEntity<ApiResponse<AnalyzeResponse>> analyzeText(

            @Valid
            @RequestBody
            AnalyzeTextRequest request
    ) {

        AnalyzeResponse response =
                analysisService.analyzeText(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Analysis completed successfully.",
                        response
                )
        );
    }
}