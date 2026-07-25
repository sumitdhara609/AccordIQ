package com.accordiq.document.dto.response;

import com.accordiq.document.enums.DocumentStatus;

import java.util.UUID;

public record DocumentResponse(
        UUID id,
        String originalFileName,
        String contentType,
        Long fileSize,
        DocumentStatus status
) {
}