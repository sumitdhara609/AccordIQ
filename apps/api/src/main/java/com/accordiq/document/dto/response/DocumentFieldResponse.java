package com.accordiq.document.dto.response;

public record DocumentFieldResponse(

        String name,

        String value,

        Double confidence

) {
}