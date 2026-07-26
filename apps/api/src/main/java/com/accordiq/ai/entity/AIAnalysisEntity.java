package com.accordiq.ai.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.entity.Document;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ai_analysis")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIAnalysisEntity extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id", nullable = false, unique = true)
    private Document document;

    @Lob
    @Column(name = "ocr_text", columnDefinition = "TEXT", nullable = false)
    private String ocrText;

    @Column(name = "ai_json", columnDefinition = "jsonb", nullable = false)
    private String aiJson;

    @Column(name = "model_name", length = 100)
    private String modelName;

    @Column(name = "processing_time_ms")
    private Long processingTimeMs;
}