package com.accordiq.storage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    private final Path uploadDirectory;

    public LocalFileStorageService(
            @Value("${accordiq.storage.location:uploads}") String uploadPath) {

        this.uploadDirectory = Paths.get(uploadPath)
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create upload directory.", e);
        }
    }

    @Override
    public String store(MultipartFile file) throws IOException {

        String extension = "";

        String originalName = file.getOriginalFilename();

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf('.'));
        }

        String storedName = UUID.randomUUID() + extension;

        Files.copy(
                file.getInputStream(),
                uploadDirectory.resolve(storedName),
                StandardCopyOption.REPLACE_EXISTING
        );

        return storedName;
    }

    @Override
    public void delete(String storedFileName) throws IOException {
        Files.deleteIfExists(uploadDirectory.resolve(storedFileName));
    }
}