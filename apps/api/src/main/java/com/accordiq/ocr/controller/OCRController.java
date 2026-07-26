package com.accordiq.ocr.controller;

import com.accordiq.analysis.application.DocumentAnalysisApplicationService;
import com.accordiq.ocr.dto.response.OCRResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ocr")
public class OCRController {

    private final DocumentAnalysisApplicationService analysisApplicationService;

    public OCRController(
            DocumentAnalysisApplicationService analysisApplicationService
    ) {
        this.analysisApplicationService = analysisApplicationService;
    }

    @PostMapping("/{documentId}")
    public ResponseEntity<OCRResponse> processOCR(
            @PathVariable UUID documentId
    ) throws IOException {

        OCRResponse response =
                analysisApplicationService.analyzeDocument(documentId);

        return ResponseEntity.ok(response);
    }
}