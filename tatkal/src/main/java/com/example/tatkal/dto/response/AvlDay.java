package com.example.tatkal.dto.response;

import lombok.Data;

@Data
public class AvlDay {
    private String availablityDate;
    private String availablityStatus;
    private String reasonType;
    private String availablityType;
    private String currentBkgFlag;
    private String wlType;
    private String delayFlag;
    private String delay;

    // Getters and Setters
}
