package com.example.tatkal.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BookNowAfterCheckAvailReqDTO {
    private String clusterFlag;
    private String onwardFlag;
    private boolean cod;
    private String reservationMode;
    private boolean autoUpgradationSelected;
    private boolean gnToCkOpted;
    private int paymentType;
    private boolean twoPhaseAuthRequired;
    private int captureAddress;
    private List<AlternateAvlInputDTO> alternateAvlInputDTO;
    private boolean passBooking;
    private boolean journalistBooking;
}

