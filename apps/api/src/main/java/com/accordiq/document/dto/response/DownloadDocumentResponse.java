package com.accordiq.document.dto.response;

import org.springframework.core.io.Resource;

public record DownloadDocumentResponse(
        Resource resource,
        String originalFileName,
        String contentType
) {
}