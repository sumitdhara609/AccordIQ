package com.accordiq.ai.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentAnalysisRequest {

    private String documentId;

    private String documentType;

    private String extractedText;

}