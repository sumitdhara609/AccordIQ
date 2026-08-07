package com.accordiq.documentanalysis.repository;

import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DocumentAnalysisRepository
        extends JpaRepository<DocumentAnalysis, UUID> {

    /**
     * Finds the AI analysis associated with a document.
     *
     * @param documentId Document ID
     * @return Document analysis if present
     */
    Optional<DocumentAnalysis> findByDocumentId(
            UUID documentId
    );

}