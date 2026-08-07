package com.accordiq.review.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReviewRequest(

        @NotBlank
        String comments

) {
}