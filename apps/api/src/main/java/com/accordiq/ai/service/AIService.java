package com.accordiq.ai.service;

import com.accordiq.ai.dto.AIResponse;

public interface AIService {

    AIResponse extract(String ocrText);

}