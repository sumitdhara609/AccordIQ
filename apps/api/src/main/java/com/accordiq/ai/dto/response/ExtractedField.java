package com.accordiq.ai.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtractedField {

    private String name;

    private String value;

    private Double confidence;

}