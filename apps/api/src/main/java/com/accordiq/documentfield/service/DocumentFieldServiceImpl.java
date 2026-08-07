package com.accordiq.documentfield.service;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.ai.dto.response.ExtractedField;
import com.accordiq.common.exception.ResourceNotFoundException;
import com.accordiq.documentanalysis.entity.DocumentAnalysis;
import com.accordiq.documentfield.dto.request.UpdateFieldRequest;
import com.accordiq.documentfield.dto.response.DocumentFieldResponse;
import com.accordiq.documentfield.entity.DocumentField;
import com.accordiq.documentfield.repository.DocumentFieldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentFieldServiceImpl
        implements DocumentFieldService {

    private final DocumentFieldRepository repository;

    public DocumentFieldServiceImpl(
            DocumentFieldRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void saveFields(
            DocumentAnalysis analysis,
            DocumentAnalysisResponse response
    ) {

        if (response.getFields() == null ||
                response.getFields().isEmpty()) {
            return;
        }

        for (ExtractedField extractedField : response.getFields()) {

            DocumentField field =
                    DocumentField.builder()
                            .analysis(analysis)
                            .fieldName(
                                    extractedField.getName()
                            )
                            .fieldValue(
                                    extractedField.getValue()
                            )
                            .confidence(
                                    extractedField.getConfidence()
                            )
                            .build();

            repository.save(field);
        }
    }

    @Override
    public DocumentFieldResponse updateField(
            java.util.UUID fieldId,
            UpdateFieldRequest request
    ) {

        DocumentField field =
                repository.findById(fieldId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Document field not found: "
                                                + fieldId
                                )
                        );

        field.setFieldValue(
                request.value()
        );

        repository.save(field);

        return new DocumentFieldResponse(

                field.getId(),

                field.getFieldName(),

                field.getFieldValue(),

                field.getConfidence()

        );

    }

}