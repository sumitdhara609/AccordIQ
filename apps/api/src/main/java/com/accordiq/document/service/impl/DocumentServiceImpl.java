package com.accordiq.document.service.impl;

import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.processing.DocumentProcessingService;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.document.service.DocumentService;
import com.accordiq.storage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;
    private final DocumentProcessingService documentProcessingService;

    @Override
    public UploadDocumentResponse upload(MultipartFile file) throws IOException {

        log.info("Uploading document '{}'", file.getOriginalFilename());

        String storedFileName = fileStorageService.store(file);

        Document document = Document.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .storagePath(
                        fileStorageService
                                .getStorageLocation()
                                .resolve(storedFileName)
                                .toString()
                )
                .build();

        Document savedDocument = documentRepository.save(document);

        log.info(
                "Document {} stored successfully.",
                savedDocument.getId()
        );

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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
}