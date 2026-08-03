package com.accordiq.ai.service;

/**
 * Common abstraction for all AI providers used by AccordIQ.
 */
public interface AIService {

    /**
     * Generates a response for the given prompt.
     *
     * @param prompt Prompt sent to the AI model
     * @return Generated response
     */
    String generateContent(String prompt);

}