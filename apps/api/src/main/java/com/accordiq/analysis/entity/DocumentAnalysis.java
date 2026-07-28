package com.accordiq.analysis.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.entity.Document;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "document_analysis",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_analysis_document",
                        columnNames = "document_id"
                )
        },
        indexes = {
                @Index(
                        name = "idx_analysis_document",
                        columnList = "document_id"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "document")
@EqualsAndHashCode(callSuper = true)
public class DocumentAnalysis extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_analysis_document")
    )
    private Document document;

    @Column(
        name = "raw_text",
        nullable = false,
        columnDefinition = "TEXT"
)
private String rawText;
    @Column(nullable = false, length = 100)
    private String ocrEngine;

    @Column(nullable = false, length = 20)
    private String ocrLanguage;
}