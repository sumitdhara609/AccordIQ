package com.accordiq.document.service;

import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.DownloadDocumentResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface DocumentService {

    UploadDocumentResponse upload(MultipartFile file) throws IOException;

    List<DocumentResponse> getAllDocuments();

    DocumentResponse getDocumentById(UUID id);

    DownloadDocumentResponse download(UUID id);

    void delete(UUID id) throws IOException;
}