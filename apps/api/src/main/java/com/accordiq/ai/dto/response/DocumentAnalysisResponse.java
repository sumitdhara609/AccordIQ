package com.accordiq.ai.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentAnalysisResponse {

    private String summary;

    private String documentType;

    private List<ExtractedField> fields;

    private AIAnalysisMetadata metadata;

}