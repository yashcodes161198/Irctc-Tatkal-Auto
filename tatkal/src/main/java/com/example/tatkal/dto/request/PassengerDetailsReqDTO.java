package com.example.tatkal.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PassengerDetailsReqDTO {
    private String clusterFlag;
    private String onwardFlag;
    private boolean cod;
    private String reservationMode;
    private boolean autoUpgradationSelected;
    private boolean gnToCkOpted;
    private String paymentType;
    private boolean twoPhaseAuthRequired;
    private int captureAddress;
    private String wsUserLogin;
    private boolean moreThanOneDay;
    private String clientTransactionId;
    private String boardingStation;
    private String reservationUptoStation;
    private String mobileNumber;
    private String ticketType;
    private String mainJourneyTxnId;
    private String mainJourneyPnr;
    private String captcha;
    private boolean generalistChildConfirm;
    private boolean ftBooking;
    private boolean loyaltyRedemptionBooking;
    private boolean nosbBooking;
    private int warrentType;
    private boolean ftTnCAgree;
    private int bookingChoice;
    private int bookingConfirmChoice;
    private boolean bookOnlyIfCnf;
    private String returnJourney;
    private boolean connectingJourney;
    private List<LapAvlRequestDTO> lapAvlRequestDTO;
    private GstDetails gstDetails;

}

