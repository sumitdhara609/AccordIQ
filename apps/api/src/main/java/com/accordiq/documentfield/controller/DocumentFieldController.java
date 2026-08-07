package com.accordiq.documentfield.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.documentfield.dto.request.UpdateFieldRequest;
import com.accordiq.documentfield.dto.response.DocumentFieldResponse;
import com.accordiq.documentfield.service.DocumentFieldService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/document-fields")
public class DocumentFieldController {

    private final DocumentFieldService documentFieldService;

    public DocumentFieldController(
            DocumentFieldService documentFieldService
    ) {
        this.documentFieldService = documentFieldService;
    }

    @PatchMapping("/{fieldId}")
    public ResponseEntity<ApiResponse<DocumentFieldResponse>>
    updateField(
            @PathVariable UUID fieldId,
            @Valid @RequestBody UpdateFieldRequest request
    ) {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Document field updated successfully.",

                        documentFieldService.updateField(
                                fieldId,
                                request
                        )

                )

        );

    }

}