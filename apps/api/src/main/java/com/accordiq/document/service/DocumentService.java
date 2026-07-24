package com.accordiq.document.service;

import com.accordiq.document.dto.response.UploadDocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {

    UploadDocumentResponse upload(MultipartFile file) throws IOException;

}