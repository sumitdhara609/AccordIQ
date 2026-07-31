package com.accordiq.ocr.provider.tesseract;

import com.accordiq.ocr.exception.OCRException;
import com.accordiq.ocr.model.OCRResult;
import com.accordiq.ocr.service.OCRService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class TesseractOCRService implements OCRService {

    private final Tesseract tesseract;

    @Override
    public OCRResult extractText(Path filePath) {

        long startTime = System.currentTimeMillis();

        if (filePath == null) {
            throw new OCRException("File path cannot be null.");
        }

        if (!Files.exists(filePath)) {
            throw new OCRException("File does not exist: " + filePath);
        }

        try {

            BufferedImage image = ImageIO.read(filePath.toFile());

            if (image == null) {
                throw new OCRException(
                        "Unsupported image format: " + filePath.getFileName()
                );
            }

            String extractedText = tesseract.doOCR(image);

            long processingTime =
                    System.currentTimeMillis() - startTime;

            return OCRResult.builder()
                    .extractedText(extractedText)
                    .confidence(-1.0)
                    .processingTimeMillis(processingTime)
                    .build();

        } catch (TesseractException ex) {

            throw new OCRException(
                    "Tesseract OCR failed.",
                    ex
            );

        } catch (IOException ex) {

            throw new OCRException(
                    "Unable to read image.",
                    ex
            );
        }
    }
}