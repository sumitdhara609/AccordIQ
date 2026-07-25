package com.accordiq.document.service.impl;

import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.DownloadDocumentResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.document.service.DocumentService;
import com.accordiq.storage.service.FileStorageService;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                .storagePath(
                        fileStorageService.getStorageLocation()
                                .resolve(storedFileName)
                                .toString()
                )
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

        Document document = findDocumentOrThrow(id);

        return new DocumentResponse(
                document.getId(),
                document.getOriginalFileName(),
                document.getContentType(),
                document.getFileSize(),
                document.getStatus()
        );
    }

    @Override
    public DownloadDocumentResponse download(UUID id) {

        Document document = findDocumentOrThrow(id);

        try {

            Path filePath = Path.of(document.getStoragePath());

            if (!Files.exists(filePath)) {
                throw new ResourceNotFoundException(
                        "File not found for document: " + id
                );
            }

            UrlResource resource = new UrlResource(filePath.toUri());

            return new DownloadDocumentResponse(
                    resource,
                    document.getOriginalFileName(),
                    document.getContentType()
            );

        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to load file.", e);
        }
    }

    @Override
    public void delete(UUID id) throws IOException {

        Document document = findDocumentOrThrow(id);

        fileStorageService.delete(document.getStoredFileName());

        documentRepository.delete(document);
    }

    private Document findDocumentOrThrow(UUID id) {

        return documentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Document not found with id: " + id
                        )
                );
    }
}