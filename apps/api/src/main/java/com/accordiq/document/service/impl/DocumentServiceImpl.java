package com.accordiq.document.service.impl;

import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.dto.request.DocumentSearchRequest;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.DocumentSearchResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.document.service.DocumentService;
import com.accordiq.document.specification.DocumentSpecification;
import com.accordiq.storage.service.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
                .storagePath(fileStorageService.getStorageLocation()
                        .resolve(storedFileName)
                        .toString())
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

    @Override
    public List<DocumentResponse> getAllDocuments() {

        return documentRepository.findAll()
                .stream()
                .map(document -> new DocumentResponse(
                        document.getId(),
                        document.getOriginalFileName(),
                        document.getContentType(),
                        document.getFileSize(),
                        document.getStatus()
                ))
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

        return new DocumentResponse(
                document.getId(),
                document.getOriginalFileName(),
                document.getContentType(),
                document.getFileSize(),
                document.getStatus()
        );
    }

    @Override
    public Page<DocumentSearchResponse> search(DocumentSearchRequest request) {

        Sort sort = Sort.by(
                Sort.Direction.fromString(request.getSortDirection()),
                request.getSortBy()
        );

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                sort
        );

        Specification<Document> specification =
                DocumentSpecification.hasFilename(request.getFilename())
                        .and(DocumentSpecification.hasContentType(request.getContentType()))
                        .and(DocumentSpecification.uploadedAfter(request.getUploadedFrom()))
                        .and(DocumentSpecification.uploadedBefore(request.getUploadedTo()));

        return documentRepository.findAll(specification, pageable)
                .map(document -> DocumentSearchResponse.builder()
                        .id(document.getId())
                        .originalFilename(document.getOriginalFileName())
                        .contentType(document.getContentType())
                        .fileSize(document.getFileSize())
                        .createdAt(document.getCreatedAt())
                        .build());
    }
}