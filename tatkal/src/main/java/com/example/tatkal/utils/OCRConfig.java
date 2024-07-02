package com.example.tatkal.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OCRConfig {

    @Value("${TESSDATA_PREFIX}")
    private String tessdataPrefix;

    @Bean
    public ITesseract tesseract() {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessdataPrefix);
        return tesseract;
    }
}



