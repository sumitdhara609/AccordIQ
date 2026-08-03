package com.accordiq.ai.prompt;

import com.accordiq.ai.dto.request.DocumentAnalysisRequest;
import org.springframework.stereotype.Component;

@Component
public class DocumentAnalysisPromptBuilder {

    public String build(DocumentAnalysisRequest request) {

        return """
                You are an AI document analysis engine.

                Analyse the following document text.

                Return ONLY valid JSON.

                Do not include markdown.
                Do not include explanations.
                Do not include code fences.

                JSON format:

                {
                  "summary": "...",
                  "documentType": "...",
                  "fields": [
                    {
                      "name": "...",
                      "value": "...",
                      "confidence": 0.0
                    }
                  ]
                }

                Document Text:

                %s
                """.formatted(request.getExtractedText());

    }
}