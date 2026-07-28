package com.accordiq.analysis.repository;

import com.accordiq.analysis.entity.DocumentAnalysis;
import com.accordiq.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DocumentAnalysisRepository
        extends JpaRepository<DocumentAnalysis, UUID> {

    Optional<DocumentAnalysis> findByDocument(Document document);

}