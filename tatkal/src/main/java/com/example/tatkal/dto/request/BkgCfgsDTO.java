package com.example.tatkal.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BkgCfgsDTO {
    private String seniorCitizenApplicable;
    private String foodChoiceEnabled;
    private String idRequired;
    private String bedRollFlagEnabled;
    private String maxMasterListPsgn;
    private String maxPassengers;
    private String maxInfants;
    private String minNameLength;
    private String maxNameLength;
    private String srctznAge;
    private String srctnwAge;
    private String maxARPDays;
    private String maxRetentionDays;
    private String minPassengerAge;
    private String maxPassengerAge;
    private String maxChildAge;
    private String minIdCardLength;
    private String maxIdCardLength;
    private String minPassportLength;
    private String maxPassportLength;
    private String srctzTAge;
    private String lowerBerthApplicable;
    private String newTimeTable;
    private String childBerthMandatory;
    private List<String> validIdCardTypes;
    private List<String> applicableBerthTypes;
    private String suvidhaTrain;
    private String specialTatkal;
    private String atasEnable;
    private String gatimaanTrain;
    private String travelInsuranceEnabled;
    private String travelInsuranceFareMsg;
    private String uidVerificationPsgnInputFlag;
    private String uidVerificationMasterListFlag;
    private String uidMandatoryFlag;
    private String gstDetailInputFlag;
    private String gstinPattern;
    private String forgoConcession;
    private String twoSSReleaseFlag;
    private String beyondArpBooking;
    private String acuralBooking;
    private String redemptionBooking;
    private String trainsiteId;
    private List<String> bonafideCountryList;
    private String pmfInputEnable;
    private String pmfInputMandatory;
    private String pmfInputMaxLength;
    private PaymentOption paymentOption;
    private String captureAddress;
    private String trainNo;

    // Getters and Setters
}
