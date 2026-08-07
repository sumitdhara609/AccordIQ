package com.accordiq.review.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.review.dto.request.ReviewRequest;
import com.accordiq.review.dto.response.ReviewResponse;
import com.accordiq.review.service.DocumentReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final DocumentReviewService reviewService;

    public ReviewController(
            DocumentReviewService reviewService
    ) {
        this.reviewService = reviewService;
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>>
    getPendingReviews() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Pending reviews retrieved successfully.",
                        reviewService.getPendingReviews()
                )
        );
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<ApiResponse<ReviewResponse>>
    getReview(
            @PathVariable UUID documentId
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Review retrieved successfully.",
                        reviewService.getReview(documentId)
                )
        );
    }

    @PostMapping("/{documentId}/approve")
    public ResponseEntity<ApiResponse<ReviewResponse>>
    approve(
            @PathVariable UUID documentId,
            @Valid @RequestBody ReviewRequest request
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document approved successfully.",
                        reviewService.approve(
                                documentId,
                                request
                        )
                )
        );
    }

    @PostMapping("/{documentId}/reject")
    public ResponseEntity<ApiResponse<ReviewResponse>>
    reject(
            @PathVariable UUID documentId,
            @Valid @RequestBody ReviewRequest request
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Document rejected successfully.",
                        reviewService.reject(
                                documentId,
                                request
                        )
                )
        );
    }

}