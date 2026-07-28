package com.accordiq.analysis.service;

import com.accordiq.analysis.entity.DocumentAnalysis;
import com.accordiq.analysis.repository.DocumentAnalysisRepository;
import com.accordiq.document.entity.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class DocumentAnalysisServiceImpl implements DocumentAnalysisService {

    private final DocumentAnalysisRepository repository;

    public DocumentAnalysisServiceImpl(DocumentAnalysisRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOcrResult(
            Document document,
            String rawText,
            String ocrEngine,
            String language
    ) {

        DocumentAnalysis analysis = repository.findByDocument(document)
                .orElseGet(() -> DocumentAnalysis.builder()
                        .document(document)
                        .build());

        analysis.setRawText(rawText);
        analysis.setOcrEngine(ocrEngine);
        analysis.setOcrLanguage(language);

        repository.save(analysis);

        log.info(
                "OCR analysis saved for document {}",
                document.getId()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentAnalysis> findByDocument(Document document) {
        return repository.findByDocument(document);
    }
}