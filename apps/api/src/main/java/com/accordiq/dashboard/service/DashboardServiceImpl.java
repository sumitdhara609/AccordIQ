package com.accordiq.dashboard.service;

import com.accordiq.dashboard.dto.response.DashboardStatsResponse;
import com.accordiq.dashboard.dto.response.RecentDocumentResponse;
import com.accordiq.document.entity.Document;
import com.accordiq.document.enums.DocumentStatus;
import com.accordiq.document.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final DocumentRepository documentRepository;

    public DashboardServiceImpl(
            DocumentRepository documentRepository
    ) {
        this.documentRepository = documentRepository;
    }

    @Override
    public DashboardStatsResponse getStatistics() {

        long totalDocuments =
                documentRepository.count();

        long uploadedToday =
                documentRepository.countUploadedSince(
                        LocalDateTime.now()
                                .toLocalDate()
                                .atStartOfDay()
                );

        long processing =
                documentRepository.countByStatus(
                        DocumentStatus.PROCESSING
                );

        long completed =
                documentRepository.countByStatus(
                        DocumentStatus.COMPLETED
                );

        long reviewRequired =
                documentRepository.countByStatus(
                        DocumentStatus.REVIEW_REQUIRED
                );

        long failed =
                documentRepository.countByStatus(
                        DocumentStatus.FAILED
                );

        return new DashboardStatsResponse(

                totalDocuments,

                uploadedToday,

                processing,

                completed,

                reviewRequired,

                failed

        );
    }

    @Override
    public List<RecentDocumentResponse> getRecentDocuments() {

        return documentRepository
                .findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapRecentDocument)
                .toList();
    }

    private RecentDocumentResponse mapRecentDocument(
            Document document
    ) {

        return new RecentDocumentResponse(

                document.getId(),

                document.getOriginalFileName(),

                document.getStatus(),

                document.getFileSize(),

                document.getCreatedAt()

        );
    }

}