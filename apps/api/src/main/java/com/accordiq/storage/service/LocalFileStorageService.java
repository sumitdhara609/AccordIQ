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
import java.util.Set;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024L; // 20 MB

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "application/pdf",
            "image/png",
            "image/jpeg"
    );

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

        validateFile(file);

        String originalFileName = Objects.requireNonNullElse(
                file.getOriginalFilename(),
                "document"
        );

        // Prevent directory traversal attempts
        originalFileName = Paths.get(originalFileName)
                .getFileName()
                .toString();

        String extension = "";

        int lastDotIndex = originalFileName.lastIndexOf('.');

        if (lastDotIndex != -1) {
            extension = originalFileName.substring(lastDotIndex);
        }

        String storedFileName = UUID.randomUUID() + extension;

        Path targetLocation = uploadDirectory
                .resolve(storedFileName)
                .normalize();

        if (!targetLocation.startsWith(uploadDirectory)) {
            throw new IOException("Invalid file path.");
        }

        Files.copy(
                file.getInputStream(),
                targetLocation,
                StandardCopyOption.REPLACE_EXISTING
        );

        return storedFileName;
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                    "Uploaded file cannot be empty."
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                    "Maximum allowed file size is 20 MB."
            );
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !ALLOWED_CONTENT_TYPES.contains(contentType)) {

            throw new IllegalArgumentException(
                    "Only PDF, PNG and JPEG files are allowed."
            );
        }
    }

    @Override
    public void delete(String storedFileName) throws IOException {

        if (storedFileName == null || storedFileName.isBlank()) {
            return;
        }

        Files.deleteIfExists(
                uploadDirectory.resolve(storedFileName)
        );
    }

    @Override
    public Path getStorageLocation() {
        return uploadDirectory;
    }
}