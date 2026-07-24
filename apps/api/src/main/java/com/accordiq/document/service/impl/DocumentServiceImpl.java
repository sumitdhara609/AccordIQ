package com.accordiq.document.service.impl;

import com.accordiq.document.dto.response.UploadDocumentResponse;
import com.accordiq.document.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public UploadDocumentResponse upload(MultipartFile file) throws IOException {

        return new UploadDocumentResponse(
                UUID.randomUUID(),
                file.getOriginalFilename(),
                "temporary-file",
                file.getContentType(),
                file.getSize(),
                "UPLOADED"
        );
    }
}