package com.accordiq.analysis.repository;

import com.accordiq.analysis.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnalysisRepository
        extends JpaRepository<Analysis, UUID> {

    Optional<Analysis> findByDocumentId(UUID documentId);

}