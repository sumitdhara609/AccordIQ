package com.accordiq.document.repository;

import com.accordiq.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DocumentRepository
        extends JpaRepository<Document, UUID>,
                JpaSpecificationExecutor<Document> {
}