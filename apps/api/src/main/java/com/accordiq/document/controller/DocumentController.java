package com.accordiq.document.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.document.dto.request.DocumentSearchRequest;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.DocumentSearchResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.service.DocumentService;
import org.springframework.data.domain.Page;
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

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<UploadDocumentResponse>> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        UploadDocumentResponse response = documentService.upload(file);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Document uploaded successfully.",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getAllDocuments() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Documents retrieved successfully.",
                        documentService.getAllDocuments()
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<DocumentSearchResponse>>> searchDocuments(
            @ModelAttribute DocumentSearchRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Documents retrieved successfully.",
                        documentService.search(request)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Document retrieved successfully.",
                        documentService.getDocumentById(id)
                )
        );
    }
}