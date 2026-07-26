package com.accordiq.document.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class DocumentSearchResponse {

    private UUID id;

    private String originalFilename;

    private String contentType;

    private Long fileSize;

    private LocalDateTime createdAt;
}