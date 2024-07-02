package com.example.tatkal.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import com.example.tatkal.dto.response.CaptchaResponseDTO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

    private final ITesseract tesseract;

    @Autowired
    public CaptchaService(ITesseract tesseract) {
        this.tesseract = tesseract;
    }

    public String extractCaptchaText(CaptchaResponseDTO captchaResponseDTO) throws IOException, TesseractException {
        // Decode Base64 string to byte array
        byte[] imageBytes = Base64.getDecoder().decode(captchaResponseDTO.getCaptchaQuestion());

        // Write bytes to a temporary file
        Path tempFilePath = Files.createTempFile("captcha", ".png");
        Files.write(tempFilePath, imageBytes);

        // Try to open the temporary image file
        openImageFile(tempFilePath);

        // Perform OCR on the temporary file
        try {
            File captchaImage = tempFilePath.toFile();
            String result = tesseract.doOCR(captchaImage);
            return result.trim(); // Assuming OCR result might have leading/trailing spaces
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            // Clean up: Delete the temporary file after use
//            Files.deleteIfExists(tempFilePath);
//        }
//        return "captcha";
    }

    // Method to open the image file using Runtime.exec() as a fallback
    private void openImageFile(Path imagePath) {
        try {
            File imageFile = imagePath.toFile();
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // For Windows
                String command = "cmd /c start " + imageFile.getAbsolutePath();
                Runtime.getRuntime().exec("cmd /c start " + imageFile.getAbsolutePath());
            } else if (os.contains("mac")) {
                // For macOS
                Runtime.getRuntime().exec("open " + imageFile.getAbsolutePath());
            } else {
                // For Linux/Unix
                Runtime.getRuntime().exec("xdg-open " + imageFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error opening image file: " + e.getMessage());
        }
    }
}
