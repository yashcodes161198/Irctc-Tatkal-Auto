package com.example.tatkal.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LapAvlRequestDTO {
    private String trainNo;
    private String journeyDate;
    private String fromStation;
    private String toStation;
    private String journeyClass;
    private String quota;
    private String coachId;
    private String reservationChoice;
    private boolean ignoreChoiceIfWl;
    private boolean travelInsuranceOpted;
    private int warrentType;
    private boolean coachPreferred;
    private boolean autoUpgradation;
    private boolean bookOnlyIfCnf;
    private String addMealInput;
    private boolean concessionBooking;
    private List<Passenger> passengerList;
    private String ssQuotaSplitCoach;

    // Getters and Setters

}
