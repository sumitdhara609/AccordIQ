package com.accordiq.ocr.service;

import com.accordiq.config.ocr.TesseractProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TesseractOcrService implements OcrService {

    private static final int PDF_RENDER_DPI = 300;
    private static final String ENGINE_NAME = "Tesseract OCR 5";

    private final TesseractProperties properties;

    @Override
    public String extractText(File file) {

        validateFile(file);

        log.info("Starting OCR for '{}'", file.getName());

        try {

            String extractedText;

            if (isPdf(file)) {
                extractedText = extractFromPdf(file);
            } else {
                extractedText = extractFromImage(file);
            }

            log.info("OCR completed successfully for '{}'", file.getName());

            return extractedText;

        } catch (IOException | TesseractException ex) {

            log.error("OCR failed for '{}'", file.getName(), ex);

            throw new RuntimeException(
                    "Failed to perform OCR on " + file.getAbsolutePath(),
                    ex
            );
        }
    }

    public String getEngineName() {
        return ENGINE_NAME;
    }

    public String getLanguage() {
        return properties.getLanguage();
    }

    private String extractFromImage(File image)
            throws TesseractException {

        log.info("Running OCR on image '{}'", image.getName());

        return createTesseract().doOCR(image);
    }

    private String extractFromPdf(File pdf)
            throws IOException, TesseractException {

        log.info("Running OCR on PDF '{}'", pdf.getName());

        StringBuilder text = new StringBuilder();

        try (PDDocument document = Loader.loadPDF(pdf)) {

            PDFRenderer renderer = new PDFRenderer(document);

            ITesseract tesseract = createTesseract();

            for (int page = 0; page < document.getNumberOfPages(); page++) {

                log.debug(
                        "Processing page {}/{}",
                        page + 1,
                        document.getNumberOfPages()
                );

                BufferedImage image =
                        renderer.renderImageWithDPI(page, PDF_RENDER_DPI);

                text.append(tesseract.doOCR(image));

                if (page < document.getNumberOfPages() - 1) {
                    text.append(System.lineSeparator())
                            .append(System.lineSeparator());
                }
            }
        }

        return text.toString().trim();
    }

    private ITesseract createTesseract() {

        validateTessData();

        Tesseract tesseract = new Tesseract();

        tesseract.setDatapath(properties.getDataPath());
        tesseract.setLanguage(properties.getLanguage());
        tesseract.setOcrEngineMode(properties.getEngineMode());
        tesseract.setPageSegMode(properties.getPageSegmentationMode());

        return tesseract;
    }

    private boolean isPdf(File file) {
        return file.getName().toLowerCase().endsWith(".pdf");
    }

    private void validateFile(File file) {

        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException(
                    "File does not exist: " + file.getAbsolutePath()
            );
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException(
                    "Invalid file: " + file.getAbsolutePath()
            );
        }
    }

    private void validateTessData() {

        File tessData = new File(properties.getDataPath());

        if (!tessData.exists() || !tessData.isDirectory()) {

            throw new IllegalStateException(
                    "Invalid tessdata directory: " + tessData.getAbsolutePath()
            );
        }
    }
}