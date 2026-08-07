package com.accordiq.document.processing;

import com.accordiq.document.entity.Document;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;
import com.accordiq.document.entity.Document;

public interface DocumentProcessingService {

    DocumentAnalysisResponse process(Document document);

}