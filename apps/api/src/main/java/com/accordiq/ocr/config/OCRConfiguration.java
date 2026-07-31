package com.accordiq.ocr.config;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OCRProperties.class)
public class OCRConfiguration {

    @Bean
    public Tesseract tesseract(OCRProperties properties) {

        Tesseract tesseract = new Tesseract();

        tesseract.setDatapath(properties.getDataPath());
        tesseract.setLanguage(properties.getLanguage());

        tesseract.setOcrEngineMode(
                properties.getEngineMode()
        );

        tesseract.setPageSegMode(
                properties.getPageSegmentationMode()
        );

        tesseract.setVariable(
                "user_defined_dpi",
                "300"
        );

        tesseract.setVariable(
                "preserve_interword_spaces",
                "1"
        );

        return tesseract;
    }

}