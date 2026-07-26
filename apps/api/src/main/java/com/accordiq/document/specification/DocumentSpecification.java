package com.accordiq.document.specification;

import com.accordiq.document.entity.Document;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public final class DocumentSpecification {

    private DocumentSpecification() {
    }

    public static Specification<Document> hasFilename(String filename) {
        return (root, query, cb) ->
                filename == null || filename.isBlank()
                        ? cb.conjunction()
                        : cb.like(
                                cb.lower(root.get("originalFileName")),
                                "%" + filename.toLowerCase() + "%"
                        );
    }

    public static Specification<Document> hasContentType(String contentType) {
        return (root, query, cb) ->
                contentType == null || contentType.isBlank()
                        ? cb.conjunction()
                        : cb.equal(root.get("contentType"), contentType);
    }

    public static Specification<Document> uploadedAfter(LocalDateTime from) {
        return (root, query, cb) ->
                from == null
                        ? cb.conjunction()
                        : cb.greaterThanOrEqualTo(root.get("createdAt"), from);
    }

    public static Specification<Document> uploadedBefore(LocalDateTime to) {
        return (root, query, cb) ->
                to == null
                        ? cb.conjunction()
                        : cb.lessThanOrEqualTo(root.get("createdAt"), to);
    }
}