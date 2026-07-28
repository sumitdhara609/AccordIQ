package com.accordiq.document.processing;

import com.accordiq.document.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DocumentProcessingServiceImpl
        implements DocumentProcessingService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DocumentProcessingServiceImpl.class);

    @Override
    public void process(Document document) {

        LOGGER.info(
                "Processing started for document {}",
                document.getId()
        );

        // OCR will be integrated here in Feature 011.
    }
}