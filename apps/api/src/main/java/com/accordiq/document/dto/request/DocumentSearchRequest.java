package com.accordiq.document.dto.request;

import com.accordiq.document.enums.DocumentStatus;

public record DocumentSearchRequest(

        String keyword,

        DocumentStatus status,

        String documentType

) {
}