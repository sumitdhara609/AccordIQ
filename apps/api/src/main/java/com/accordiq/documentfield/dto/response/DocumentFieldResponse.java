package com.accordiq.documentfield.dto.response;

import java.util.UUID;

public record DocumentFieldResponse(

        UUID id,

        String fieldName,

        String fieldValue,

        Double confidence

) {
}