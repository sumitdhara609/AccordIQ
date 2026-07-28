package com.accordiq.document.entity;

import com.accordiq.analysis.entity.DocumentAnalysis;
import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document extends BaseEntity {

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false, unique = true)
    private String storedFileName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String storagePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private DocumentStatus status = DocumentStatus.UPLOADED;

    @OneToOne(
            mappedBy = "document",
            fetch = FetchType.LAZY
    )
    private DocumentAnalysis analysis;
}