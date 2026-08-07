package com.accordiq.document.service;

import com.accordiq.document.dto.response.DocumentDetailsResponse;

import java.util.UUID;

public interface DocumentDetailsService {

    DocumentDetailsResponse getDocumentDetails(
            UUID documentId
    );

}