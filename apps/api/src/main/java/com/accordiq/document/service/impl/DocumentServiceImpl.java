package com.accordiq.document.service.impl;

import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.service.DocumentService;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.storage.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            FileStorageService fileStorageService
    ) {
        this.documentRepository = documentRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public UploadDocumentResponse upload(MultipartFile file) throws IOException {

        String storedFileName = fileStorageService.store(file);

        Document document = Document.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .storagePath(fileStorageService.getStorageLocation().resolve(storedFileName).toString())
                .build();

        Document savedDocument = documentRepository.save(document);

        return new UploadDocumentResponse(
                savedDocument.getId(),
                savedDocument.getOriginalFileName(),
                savedDocument.getStoredFileName(),
                savedDocument.getContentType(),
                savedDocument.getFileSize(),
                savedDocument.getStatus().name()
        );
    }
}