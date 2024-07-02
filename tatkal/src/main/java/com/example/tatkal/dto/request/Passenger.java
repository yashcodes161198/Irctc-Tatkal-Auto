package com.example.tatkal.dto.request;

import lombok.Data;

@Data
public class Passenger {
    private String passengerName;
    private int passengerAge;
    private String passengerGender;
    private String passengerBerthChoice;
    private String passengerFoodChoice;
    private String passengerBedrollChoice;
    private String passengerNationality;
    private String passengerCardTypeMaster;
    private String passengerCardNumberMaster;
    private String psgnConcType;
    private String psgnConcCardId;
    private String psgnConcDOB;
    private String psgnConcCardExpiryDate;
    private String psgnConcDOBP;
    private String softMemberId;
    private String softMemberFlag;
    private String psgnConcCardExpiryDateP;
    private boolean passengerVerified;
    private String masterPsgnId;
    private String mpMemberFlag;
    private String passengerForceNumber;
    private String passConcessionType;
    private String passUPN;
    private String passBookingCode;
    private int passengerSerialNumber;
    private boolean childBerthFlag;
    private String passengerCardType;
    private boolean passengerIcardFlag;
    private String passengerCardNumber;

    // Getters and Setters
}