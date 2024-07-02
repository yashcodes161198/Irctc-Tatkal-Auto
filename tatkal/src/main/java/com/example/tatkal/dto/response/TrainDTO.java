package com.example.tatkal.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class TrainDTO {
    private String trainNumber;
    private String trainName;
    private String fromStnCode;
    private String toStnCode;
    private String arrivalTime;
    private String departureTime;
    private String distance;
    private String duration;
    private String runningMon;
    private String runningTue;
    private String runningWed;
    private String runningThu;
    private String runningFri;
    private String runningSat;
    private String runningSun;
    private List<String> avlClasses;
    private List<String> trainType;
    private String atasOpted;
    private String flexiFlag;
    private String trainOwner;
    private String trainsiteId;

    // Getters and setters (omitted for brevity)
}