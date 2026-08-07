package com.accordiq.documentfield.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_fields")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentField extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "analysis_id",
            nullable = false
    )
    private DocumentAnalysis analysis;

    @Column(nullable = false)
    private String fieldName;

    @Column(columnDefinition = "TEXT")
    private String fieldValue;

    private Double confidence;

}