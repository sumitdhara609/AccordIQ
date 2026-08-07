package com.accordiq.analysis.dto.response;

import java.util.List;

public class AnalyzeResponse {

    private String summary;

    private List<String> keyPoints;

    private List<String> risks;

    private List<String> recommendations;

    public AnalyzeResponse() {
    }

    public AnalyzeResponse(
            String summary,
            List<String> keyPoints,
            List<String> risks,
            List<String> recommendations
    ) {
        this.summary = summary;
        this.keyPoints = keyPoints;
        this.risks = risks;
        this.recommendations = recommendations;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeyPoints() {
        return keyPoints;
    }

    public void setKeyPoints(List<String> keyPoints) {
        this.keyPoints = keyPoints;
    }

    public List<String> getRisks() {
        return risks;
    }

    public void setRisks(List<String> risks) {
        this.risks = risks;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}