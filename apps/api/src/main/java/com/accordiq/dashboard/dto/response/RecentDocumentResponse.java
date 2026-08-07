package com.accordiq.dashboard.dto.response;

import com.accordiq.document.enums.DocumentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record RecentDocumentResponse(

        UUID id,

        String fileName,

        DocumentStatus status,

        Long fileSize,

        LocalDateTime uploadedAt

) {
}