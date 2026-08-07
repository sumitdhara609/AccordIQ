package com.accordiq.document.dto.response;

import com.accordiq.ai.dto.response.DocumentAnalysisResponse;

public record UploadAnalysisResponse(

        UploadDocumentResponse upload,

        DocumentAnalysisResponse analysis

) {
}