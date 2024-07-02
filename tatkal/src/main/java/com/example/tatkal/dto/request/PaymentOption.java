package com.example.tatkal.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PaymentOption {
    private String header;
    private String type;
    private String defaultValue;
    private List<PaymentOptionDetail> options;

    // Getters and Setters
}
