package com.accordiq.documentanalysis.service;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.document.entity.Document;
import com.accordiq.documentanalysis.dto.response.DocumentAnalysisDetailResponse;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import com.accordiq.documentanalysis.repository.DocumentAnalysisRepository;
import com.accordiq.documentfield.dto.response.DocumentFieldResponse;
import com.accordiq.documentfield.entity.DocumentField;
import com.accordiq.documentfield.repository.DocumentFieldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DocumentAnalysisServiceImpl
        implements DocumentAnalysisService {

    private final DocumentAnalysisRepository repository;

    private final DocumentFieldRepository documentFieldRepository;

    public DocumentAnalysisServiceImpl(
            DocumentAnalysisRepository repository,
            DocumentFieldRepository documentFieldRepository
    ) {
        this.repository = repository;
        this.documentFieldRepository = documentFieldRepository;
    }

    @Override
    public DocumentAnalysis saveAnalysis(
            Document document,
            DocumentAnalysisResponse response
    ) {

        DocumentAnalysis analysis =
                DocumentAnalysis.builder()
                        .document(document)
                        .summary(response.getSummary())
                        .documentType(response.getDocumentType())
                        .confidence(null)
                        .rawResponse(response.toString())
                        .build();

        return repository.save(analysis);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentAnalysisDetailResponse getAnalysis(
            UUID documentId
    ) {

        DocumentAnalysis analysis =
                repository.findByDocumentId(documentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Analysis not found for document: "
                                                + documentId
                                )
                        );

        List<DocumentFieldResponse> fields =
                documentFieldRepository
                        .findByAnalysisId(analysis.getId())
                        .stream()
                        .map(this::mapField)
                        .toList();

        return new DocumentAnalysisDetailResponse(

                analysis.getId(),

                analysis.getDocument().getId(),

                analysis.getDocumentType(),

                analysis.getSummary(),

                analysis.getConfidence(),

                fields

        );
    }

    private DocumentFieldResponse mapField(
            DocumentField field
    ) {

        return new DocumentFieldResponse(

                field.getId(),

                field.getFieldName(),

                field.getFieldValue(),

                field.getConfidence()

        );
    }
}