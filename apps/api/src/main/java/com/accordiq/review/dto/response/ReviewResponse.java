package com.accordiq.review.dto.response;

import com.accordiq.review.enums.ReviewStatus;

import java.util.UUID;

public record ReviewResponse(

        UUID id,

        UUID documentId,

        ReviewStatus status,

        String reviewerComments

) {
}