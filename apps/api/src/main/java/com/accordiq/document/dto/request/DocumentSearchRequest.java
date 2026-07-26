package com.accordiq.document.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentSearchRequest {

    private String filename;

    private String contentType;

    private LocalDateTime uploadedFrom;

    private LocalDateTime uploadedTo;

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

    @Builder.Default
    private String sortBy = "createdAt";

    @Builder.Default
    private String sortDirection = "desc";
}