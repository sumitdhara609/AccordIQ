package com.accordiq.document.repository;

import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    List<Document> findAllByOrderByCreatedAtDesc();

    List<Document> findByOriginalFileNameContainingIgnoreCaseOrderByCreatedAtDesc(
            String keyword
    );

    List<Document> findByStatusOrderByCreatedAtDesc(
            DocumentStatus status
    );

    List<Document> findByOriginalFileNameContainingIgnoreCaseAndStatusOrderByCreatedAtDesc(
            String keyword,
            DocumentStatus status
    );
}