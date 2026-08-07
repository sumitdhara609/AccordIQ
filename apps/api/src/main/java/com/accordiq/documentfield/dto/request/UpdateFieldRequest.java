package com.accordiq.documentfield.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateFieldRequest(

        @NotBlank
        String value

) {
}