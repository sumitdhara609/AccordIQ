package com.accordiq.document.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
                new ApiResponse<>(
                        true,
                        "Document uploaded successfully.",
                        response
                )
        );
    }
}