package com.accordiq.document.service.impl;

import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.processing.DocumentProcessingService;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.document.service.DocumentService;
import com.accordiq.storage.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;
    private final DocumentProcessingService documentProcessingService;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            FileStorageService fileStorageService,
            DocumentProcessingService documentProcessingService
    ) {
        this.documentRepository = documentRepository;
        this.fileStorageService = fileStorageService;
        this.documentProcessingService = documentProcessingService;
    }

    @Override
    public UploadDocumentResponse upload(MultipartFile file) throws IOException {

        String storedFileName = fileStorageService.store(file);

        Document document = Document.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .storagePath(
                        fileStorageService.getStorageLocation()
                                .resolve(storedFileName)
                                .toString()
                )
                .build();

        Document savedDocument = documentRepository.save(document);

        documentProcessingService.process(savedDocument);

        return new UploadDocumentResponse(
                savedDocument.getId(),
                savedDocument.getOriginalFileName(),
                savedDocument.getStoredFileName(),
                savedDocument.getContentType(),
                savedDocument.getFileSize(),
                savedDocument.getStatus().name()
        );
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {

        return documentRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentResponse getDocumentById(UUID id) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Document not found with id: " + id
                        )
                );

        return mapToResponse(document);
    }

    @Override
    public void deleteDocument(UUID id) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Document not found with id: " + id
                        )
                );

        try {

            Path filePath = Path.of(document.getStoragePath());

            Files.deleteIfExists(filePath);

        } catch (IOException exception) {

            throw new RuntimeException(
                    "Failed to delete document from storage.",
                    exception
            );
        }

        documentRepository.delete(document);
    }

    @Override
    public List<DocumentResponse> searchDocuments(
            String keyword,
            DocumentStatus status
    ) {

        keyword = keyword == null ? null : keyword.trim();

        List<Document> documents;

        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasStatus = status != null;

        if (hasKeyword && hasStatus) {

            documents = documentRepository
                    .findByOriginalFileNameContainingIgnoreCaseAndStatusOrderByCreatedAtDesc(
                            keyword,
                            status
                    );

        } else if (hasKeyword) {

            documents = documentRepository
                    .findByOriginalFileNameContainingIgnoreCaseOrderByCreatedAtDesc(
                            keyword
                    );

        } else if (hasStatus) {

            documents = documentRepository
                    .findByStatusOrderByCreatedAtDesc(status);

        } else {

            documents = documentRepository
                    .findAllByOrderByCreatedAtDesc();
        }

        return documents.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DocumentResponse mapToResponse(Document document) {

        return new DocumentResponse(
                document.getId(),
                document.getOriginalFileName(),
                document.getContentType(),
                document.getFileSize(),
                document.getStatus()
        );
    }
}