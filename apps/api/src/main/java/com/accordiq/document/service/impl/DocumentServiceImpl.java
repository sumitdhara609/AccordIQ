package com.accordiq.document.service.impl;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.dto.request.DocumentSearchRequest;
import com.accordiq.document.dto.response.DocumentResponse;
import com.accordiq.document.dto.response.UploadAnalysisResponse;
import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.processing.DocumentProcessingService;
import com.accordiq.document.repository.DocumentRepository;
import com.accordiq.document.service.DocumentService;
import com.accordiq.storage.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DocumentServiceImpl.class);

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
    public UploadAnalysisResponse upload(
            MultipartFile file
    ) throws IOException {

        /*
         * Store the uploaded file temporarily.
         *
         * The physical file is removed in the finally block
         * after OCR/AI processing completes.
         */
        String storedFileName =
                fileStorageService.store(file);

        try {

            Document document =
                    Document.builder()
                            .originalFileName(
                                    file.getOriginalFilename()
                            )
                            .storedFileName(
                                    storedFileName
                            )
                            .contentType(
                                    file.getContentType()
                            )
                            .fileSize(
                                    file.getSize()
                            )
                            .storagePath(
                                    fileStorageService
                                            .getStorageLocation()
                                            .resolve(storedFileName)
                                            .toString()
                            )
                            .build();

            Document savedDocument =
                    documentRepository.save(document);

            try {

                /*
                 * OCR + AI analysis + persistence of the
                 * resulting analysis and extracted fields.
                 */
                DocumentAnalysisResponse analysis =
                        documentProcessingService.process(
                                savedDocument
                        );

                UploadDocumentResponse upload =
                        new UploadDocumentResponse(

                                savedDocument.getId(),

                                savedDocument.getOriginalFileName(),

                                savedDocument.getStoredFileName(),

                                savedDocument.getContentType(),

                                savedDocument.getFileSize(),

                                savedDocument.getStatus().name()

                        );

                return new UploadAnalysisResponse(
                        upload,
                        analysis
                );

            } catch (RuntimeException exception) {

                /*
                 * If document processing fails, explicitly
                 * persist FAILED from this outer workflow.
                 */
                savedDocument.setStatus(
                        DocumentStatus.FAILED
                );

                documentRepository.save(
                        savedDocument
                );

                throw exception;
            }

        } finally {

            /*
             * Uploaded files are temporary processing artifacts.
             *
             * They are removed whether processing succeeds
             * or fails.
             */
            try {

                fileStorageService.delete(
                        storedFileName
                );

                LOGGER.info(
                        "Temporary uploaded file removed: {}",
                        storedFileName
                );

            } catch (IOException cleanupException) {

                /*
                 * Cleanup failure must not hide the original
                 * upload or processing exception.
                 */
                LOGGER.warn(
                        "Failed to remove temporary uploaded file: {}",
                        storedFileName,
                        cleanupException
                );
            }
        }
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {

        return documentRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentResponse getDocumentById(
            UUID id
    ) {

        Document document =
                documentRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Document not found with id: "
                                                + id
                                )
                        );

        return mapToResponse(document);
    }

    @Override
    public void deleteDocument(
            UUID id
    ) {

        Document document =
                documentRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Document not found with id: "
                                                + id
                                )
                        );

        try {

            /*
             * Storage concerns remain inside
             * FileStorageService.
             *
             * The file may already have been removed after
             * processing, so delete() safely handles a
             * missing file.
             */
            fileStorageService.delete(
                    document.getStoredFileName()
            );

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

        keyword =
                keyword == null
                        ? null
                        : keyword.trim();

        List<Document> documents;

        boolean hasKeyword =
                keyword != null && !keyword.isBlank();

        boolean hasStatus =
                status != null;

        if (hasKeyword && hasStatus) {

            documents =
                    documentRepository
                            .findByOriginalFileNameContainingIgnoreCaseAndStatusOrderByCreatedAtDesc(
                                    keyword,
                                    status
                            );

        } else if (hasKeyword) {

            documents =
                    documentRepository
                            .findByOriginalFileNameContainingIgnoreCaseOrderByCreatedAtDesc(
                                    keyword
                            );

        } else if (hasStatus) {

            documents =
                    documentRepository
                            .findByStatusOrderByCreatedAtDesc(
                                    status
                            );

        } else {

            documents =
                    documentRepository
                            .findAllByOrderByCreatedAtDesc();

        }

        return documents
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<DocumentResponse> advancedSearch(
            DocumentSearchRequest request
    ) {

        /*
         * Phase 1 implementation.
         *
         * Currently delegates to the existing search API.
         * Document-type and extracted-field searching can
         * be added in a later search enhancement.
         */
        return searchDocuments(
                request.keyword(),
                request.status()
        );
    }

    private DocumentResponse mapToResponse(
            Document document
    ) {

        return new DocumentResponse(

                document.getId(),

                document.getOriginalFileName(),

                document.getContentType(),

                document.getFileSize(),

                document.getStatus()

        );
    }
}