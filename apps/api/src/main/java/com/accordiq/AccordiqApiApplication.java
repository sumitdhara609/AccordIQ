package com.accordiq;

import com.accordiq.ai.config.AIProperties;
import com.accordiq.config.ocr.OCRProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        OCRProperties.class,
        AIProperties.class
})
public class AccordiqApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccordiqApiApplication.class, args);
    }

}