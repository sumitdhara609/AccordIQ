package com.accordiq.document.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.document.dto.response.DocumentDetailsResponse;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.UploadAnalysisResponse;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.service.DocumentDetailsService;
import com.accordiq.document.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    private final DocumentDetailsService documentDetailsService;

    public DocumentController(
            DocumentService documentService,
            DocumentDetailsService documentDetailsService
    ) {
        this.documentService = documentService;
        this.documentDetailsService = documentDetailsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<UploadAnalysisResponse>> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        UploadAnalysisResponse response =
                documentService.upload(file);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document uploaded and analyzed successfully.",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getAllDocuments() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Documents retrieved successfully.",
                        documentService.getAllDocuments()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document retrieved successfully.",
                        documentService.getDocumentById(id)
                )
        );
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ApiResponse<DocumentDetailsResponse>>
    getDocumentDetails(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document details retrieved successfully.",
                        documentDetailsService.getDocumentDetails(id)
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DocumentResponse>>>
    searchDocuments(

            @RequestParam(required = false)
            String keyword,

            @RequestParam(required = false)
            DocumentStatus status
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search completed successfully.",
                        documentService.searchDocuments(
                                keyword,
                                status
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDocument(
            @PathVariable UUID id
    ) {

        documentService.deleteDocument(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document deleted successfully.",
                        null
                )
        );
    }

}