-- ===========================================================
-- AccordIQ Initial Database Schema
-- Version: V1
-- Database: PostgreSQL
-- ===========================================================

CREATE TABLE documents
(
    id UUID PRIMARY KEY,

    original_file_name VARCHAR(255) NOT NULL,
    stored_file_name VARCHAR(255) NOT NULL UNIQUE,
    content_type VARCHAR(255) NOT NULL,
    file_size BIGINT NOT NULL,
    storage_path VARCHAR(1000) NOT NULL,

    status VARCHAR(50) NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE ai_analysis
(
    id UUID PRIMARY KEY,

    document_id UUID NOT NULL UNIQUE,

    ocr_text TEXT NOT NULL,

    ai_json JSONB NOT NULL,

    model_name VARCHAR(100) NOT NULL,

    processing_time_ms BIGINT,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    version BIGINT NOT NULL DEFAULT 0,

    CONSTRAINT fk_ai_document
        FOREIGN KEY (document_id)
        REFERENCES documents(id)
        ON DELETE CASCADE
);

-- ===========================================================
-- Indexes
-- ===========================================================

CREATE INDEX idx_documents_status
    ON documents(status);

CREATE INDEX idx_documents_created_at
    ON documents(created_at DESC);

CREATE INDEX idx_ai_document
    ON ai_analysis(document_id);

CREATE INDEX idx_ai_created_at
    ON ai_analysis(created_at DESC);

CREATE INDEX idx_ai_json
    ON ai_analysis
    USING GIN (ai_json);