package com.accordiq.storage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorageService {

    /**
     * Stores the uploaded file and returns the generated filename.
     */
    String store(MultipartFile file) throws IOException;

    /**
     * Deletes a previously stored file.
     */
    void delete(String storedFileName) throws IOException;

    /**
     * Returns the root storage directory.
     */
    Path getStorageLocation();
}