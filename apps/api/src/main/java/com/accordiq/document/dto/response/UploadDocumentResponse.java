package com.accordiq.document.dto.response;

import java.util.UUID;

public record UploadDocumentResponse(
        UUID id,
        String originalFileName,
        String storedFileName,
        String contentType,
        Long fileSize,
        String status
) {
}