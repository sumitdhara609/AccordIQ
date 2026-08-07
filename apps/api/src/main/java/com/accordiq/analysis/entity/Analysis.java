package com.accordiq.analysis.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.entity.Document;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analysis extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            unique = true
    )
    private Document document;

    @Lob
    @Column(nullable = false)
    private String summary;

    @Column(nullable =false)
    private String documentType;

    @Lob
    private String extractedFieldsJson;
}