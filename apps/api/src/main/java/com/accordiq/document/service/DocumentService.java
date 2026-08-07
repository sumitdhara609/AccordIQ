package com.accordiq.document.service;

import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.UploadAnalysisResponse;
import com.accordiq.document.enums.DocumentStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface DocumentService {

    UploadAnalysisResponse upload(MultipartFile file) throws IOException;

    List<DocumentResponse> getAllDocuments();

    DocumentResponse getDocumentById(UUID id);

    void deleteDocument(UUID id);

    List<DocumentResponse> searchDocuments(
            String keyword,
            DocumentStatus status
    );
}