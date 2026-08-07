package com.accordiq.documentanalysis.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.entity.Document;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_analyses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAnalysis extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            unique = true
    )
    private Document document;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(nullable = false)
    private String documentType;

    private Double confidence;

    @Column(columnDefinition = "TEXT")
    private String rawResponse;

}