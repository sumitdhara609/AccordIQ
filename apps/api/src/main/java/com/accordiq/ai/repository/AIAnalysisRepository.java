package com.accordiq.ai.repository;

import com.accordiq.ai.entity.AIAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AIAnalysisRepository
        extends JpaRepository<AIAnalysisEntity, UUID> {

    Optional<AIAnalysisEntity> findByDocument_Id(UUID documentId);

    boolean existsByDocument_Id(UUID documentId);

    void deleteByDocument_Id(UUID documentId);

}