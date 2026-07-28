package com.accordiq.analysis.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.entity.Document;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "document_analysis",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "document_id")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAnalysis extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_analysis_document")
    )
    private Document document;

    @Lob
    @Column(nullable = false)
    private String rawText;

    @Column(nullable = false, length = 100)
    private String ocrEngine;

    @Column(nullable = false, length = 20)
    private String ocrLanguage;
}