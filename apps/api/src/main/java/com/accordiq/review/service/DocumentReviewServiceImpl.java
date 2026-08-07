package com.accordiq.review.service;

import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.review.dto.request.ReviewRequest;
import com.accordiq.review.dto.response.ReviewResponse;
import com.accordiq.review.entity.DocumentReview;
import com.accordiq.review.enums.ReviewStatus;
import com.accordiq.review.repository.DocumentReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DocumentReviewServiceImpl
        implements DocumentReviewService {

    private final DocumentReviewRepository reviewRepository;

    private final DocumentRepository documentRepository;

    public DocumentReviewServiceImpl(
            DocumentReviewRepository reviewRepository,
            DocumentRepository documentRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public ReviewResponse approve(
            UUID documentId,
            ReviewRequest request
    ) {

        DocumentReview review = getOrCreate(documentId);

        review.setStatus(
                ReviewStatus.APPROVED
        );

        review.setReviewerComments(
                request.comments()
        );

        reviewRepository.save(review);

        updateDocumentStatus(
                documentId,
                DocumentStatus.COMPLETED
        );

        return map(review);

    }

    @Override
    public ReviewResponse reject(
            UUID documentId,
            ReviewRequest request
    ) {

        DocumentReview review = getOrCreate(documentId);

        review.setStatus(
                ReviewStatus.REJECTED
        );

        review.setReviewerComments(
                request.comments()
        );

        reviewRepository.save(review);

        updateDocumentStatus(
                documentId,
                DocumentStatus.REVIEW_REQUIRED
        );

        return map(review);

    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse getReview(
            UUID documentId
    ) {

        DocumentReview review =
                reviewRepository
                        .findByDocumentId(documentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Review not found for document: "
                                                + documentId
                                )
                        );

        return map(review);

    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getPendingReviews() {

        return reviewRepository
                .findByStatusOrderByCreatedAtAsc(
                        ReviewStatus.PENDING
                )
                .stream()
                .map(this::map)
                .toList();

    }

    private DocumentReview getOrCreate(
            UUID documentId
    ) {

        return reviewRepository
                .findByDocumentId(documentId)
                .orElseGet(() -> {

                    Document document =
                            documentRepository
                                    .findById(documentId)
                                    .orElseThrow(() ->
                                            new ResourceNotFoundException(
                                                    "Document not found: "
                                                            + documentId
                                            )
                                    );

                    DocumentReview review =
                            DocumentReview.builder()
                                    .document(document)
                                    .status(
                                            ReviewStatus.PENDING
                                    )
                                    .build();

                    return reviewRepository.save(review);

                });

    }

    private void updateDocumentStatus(
            UUID documentId,
            DocumentStatus status
    ) {

        Document document =
                documentRepository
                        .findById(documentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Document not found: "
                                                + documentId
                                )
                        );

        document.setStatus(status);

        documentRepository.save(document);

    }

    private ReviewResponse map(
            DocumentReview review
    ) {

        return new ReviewResponse(

                review.getId(),

                review.getDocument().getId(),

                review.getStatus(),

                review.getReviewerComments()

        );

    }

}