package com.accordiq.review.service;

import com.accordiq.review.dto.request.ReviewRequest;
import com.accordiq.review.dto.response.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface DocumentReviewService {

    ReviewResponse approve(
            UUID documentId,
            ReviewRequest request
    );

    ReviewResponse reject(
            UUID documentId,
            ReviewRequest request
    );

    ReviewResponse getReview(
            UUID documentId
    );

    List<ReviewResponse> getPendingReviews();

}