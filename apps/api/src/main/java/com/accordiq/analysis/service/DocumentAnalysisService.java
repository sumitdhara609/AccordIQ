package com.accordiq.analysis.service;

import com.accordiq.analysis.entity.DocumentAnalysis;
import com.accordiq.document.entity.Document;

import java.util.Optional;

public interface DocumentAnalysisService {

    void saveOcrResult(
            Document document,
            String rawText,
            String ocrEngine,
            String language
    );

    Optional<DocumentAnalysis> findByDocument(Document document);
}