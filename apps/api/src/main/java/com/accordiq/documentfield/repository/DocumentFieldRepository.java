package com.accordiq.documentfield.repository;

import com.accordiq.documentfield.entity.DocumentField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentFieldRepository
        extends JpaRepository<DocumentField, UUID> {

    List<DocumentField> findByAnalysisId(
            UUID analysisId
    );

    Optional<DocumentField> findByIdAndAnalysisId(
            UUID fieldId,
            UUID analysisId
    );

}