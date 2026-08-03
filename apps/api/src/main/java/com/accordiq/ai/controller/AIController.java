package com.accordiq.ai.controller;

import com.accordiq.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @GetMapping("/test")
    public ResponseEntity<String> testGemini() {

        String response = aiService.generateContent(
                "Reply with exactly this sentence and nothing else: Gemini connection successful."
        );

        return ResponseEntity.ok(response);
    }
}