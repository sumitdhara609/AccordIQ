package com.accordiq.ocr.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "ocr.tesseract")
public class OCRProperties {

    private String dataPath;

    private String language = "eng";

    private int engineMode = 1;

    private int pageSegmentationMode = 3;

}