package com.accordiq.ai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AIAnalysis(

        String documentType,

        String summary,

        Entities entities,

        List<String> keyPoints

) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Entities(

            List<String> people,

            List<String> organizations,

            List<String> dates,

            List<String> amounts

    ) {
    }

}