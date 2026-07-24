package com.accordiq.storage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String store(MultipartFile file) throws IOException;

    void delete(String storedFileName) throws IOException;

}