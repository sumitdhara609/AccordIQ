package com.accordiq.ai.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AIAnalysisMetadata {

    private String model;

    private Long processingTimeMillis;

    private Integer totalFields;

}