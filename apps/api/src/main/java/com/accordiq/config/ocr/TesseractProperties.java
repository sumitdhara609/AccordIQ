package com.accordiq.config.ocr;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "accordiq.ocr.tesseract")
public class TesseractProperties {

    /**
     * Absolute path to the tessdata directory.
     */
    private String dataPath;

    /**
     * OCR language.
     */
    private String language = "eng";

    /**
     * OCR engine mode.
     *
     * 0 = Legacy
     * 1 = LSTM
     * 2 = Legacy + LSTM
     * 3 = Default
     */
    private int engineMode = 1;

    /**
     * Page segmentation mode.
     */
    private int pageSegmentationMode = 3;
}