package com.example.tatkal.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardingStationDTO {
    private String stnNameCode;
    private String arrivalTime;
    private String departureTime;
    private String routeNumber;
    private String haltTime;
    private String distance;
    private String dayCount;
    private String stnSerialNumber;
    private LocalDateTime boardingDate;

    // Getters and Setters
}
