package com.accordiq.storage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    private final Path uploadDirectory;

    public LocalFileStorageService(
            @Value("${accordiq.storage.location:uploads}") String uploadPath
    ) {

        this.uploadDirectory = Paths.get(uploadPath)
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory.", e);
        }
    }

    @Override
    public String store(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file cannot be empty.");
        }

        String originalFileName = Objects.requireNonNullElse(
                file.getOriginalFilename(),
                "document"
        );

        String extension = "";

        int lastDotIndex = originalFileName.lastIndexOf('.');

        if (lastDotIndex != -1) {
            extension = originalFileName.substring(lastDotIndex);
        }

        String storedFileName = UUID.randomUUID() + extension;

        Path targetLocation = uploadDirectory.resolve(storedFileName);

        Files.copy(
                file.getInputStream(),
                targetLocation,
                StandardCopyOption.REPLACE_EXISTING
        );

        return storedFileName;
    }

    @Override
    public void delete(String storedFileName) throws IOException {

        if (storedFileName == null || storedFileName.isBlank()) {
            return;
        }

        Files.deleteIfExists(uploadDirectory.resolve(storedFileName));
    }

    @Override
    public Path getStorageLocation() {
        return uploadDirectory;
    }
}