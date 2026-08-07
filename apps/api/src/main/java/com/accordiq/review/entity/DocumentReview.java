package com.accordiq.review.entity;

import com.accordiq.common.entity.BaseEntity;
import com.accordiq.document.entity.Document;
import com.accordiq.review.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_reviews")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentReview extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            unique = true
    )
    private Document document;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus status;

    @Column(columnDefinition = "TEXT")
    private String reviewerComments;

}