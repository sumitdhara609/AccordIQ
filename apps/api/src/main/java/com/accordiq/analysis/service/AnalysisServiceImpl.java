package com.accordiq.analysis.service;

import com.accordiq.ai.entity.AIAnalysisEntity;
import com.accordiq.ai.repository.AIAnalysisRepository;
import com.accordiq.analysis.dto.AnalysisResponse;
import com.accordiq.analysis.exception.AnalysisNotFoundException;
import com.accordiq.analysis.mapper.AnalysisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisServiceImpl implements AnalysisService {

    private final AIAnalysisRepository repository;

    private final AnalysisMapper mapper;

    @Override
    public AnalysisResponse getAnalysis(UUID documentId) {

        AIAnalysisEntity entity =
                repository.findByDocument_Id(documentId)
                        .orElseThrow(() ->
                                new AnalysisNotFoundException(
                                        "Analysis not found for document: " + documentId
                                ));

        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void deleteAnalysis(UUID documentId) {

        if (!repository.existsByDocument_Id(documentId)) {
            throw new AnalysisNotFoundException(
                    "Analysis not found for document: " + documentId
            );
        }

        repository.deleteByDocument_Id(documentId);
    }
}