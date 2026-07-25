package com.accordiq.document.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.DownloadDocumentResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.service.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
                new ApiResponse<>(
                        true,
                        "Document uploaded successfully.",
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

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable UUID id
    ) {

        DownloadDocumentResponse response = documentService.download(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(response.contentType()))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + response.originalFileName() + "\""
                )
                .body(response.resource());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDocument(
            @PathVariable UUID id
    ) throws IOException {

        documentService.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document deleted successfully.",
                        null
                )
        );
    }
}