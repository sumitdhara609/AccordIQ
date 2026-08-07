package com.accordiq.documentanalysis.service;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import com.accordiq.documentanalysis.repository.DocumentAnalysisRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentAnalysisServiceImpl
        implements DocumentAnalysisService {

    private final DocumentAnalysisRepository repository;

    public DocumentAnalysisServiceImpl(
            DocumentAnalysisRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public DocumentAnalysis saveAnalysis(
            Document document,
            DocumentAnalysisResponse response
    ) {

        DocumentAnalysis analysis =
                DocumentAnalysis.builder()

                        .document(document)

                        .summary(
                                response.getSummary()
                        )

                        .documentType(
                                response.getDocumentType()
                        )

                        /*
                         * Confidence will be implemented
                         * in a later feature once the AI
                         * metadata contains confidence.
                         */
                        .confidence(null)

                        /*
                         * We'll replace this with proper JSON
                         * serialization later.
                         */
                        .rawResponse(
                                response.toString()
                        )

                        .build();

        return repository.save(analysis);

    }

}