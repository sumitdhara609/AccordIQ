package com.accordiq.analysis.dto.request;

import jakarta.validation.constraints.NotBlank;

public class AnalyzeTextRequest {

    @NotBlank(message = "Text must not be blank.")
    private String text;

    public AnalyzeTextRequest() {
    }

    public AnalyzeTextRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}