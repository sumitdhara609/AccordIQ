package com.accordiq.document.dto.response;

import com.accordiq.document.enums.DocumentStatus;

import java.util.List;
import java.util.UUID;

public record DocumentDetailsResponse(

        UUID id,

        String originalFileName,

        String contentType,

        Long fileSize,

        DocumentStatus status,

        String documentType,

        String summary,

        List<DocumentFieldResponse> fields

) {
}