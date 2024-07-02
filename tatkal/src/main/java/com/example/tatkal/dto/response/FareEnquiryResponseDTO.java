package com.example.tatkal.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class FareEnquiryResponseDTO {
    private String trainName;
    private String distance;
    private String reqEnqParam;
    private String quota;
    private String enqClass;
    private String from;
    private String to;
    private String trainNo;
    private String baseFare;
    private String reservationCharge;
    private String superfastCharge;
    private String fuelAmount;
    private String totalConcession;
    private String tatkalFare;
    private String serviceTax;
    private String otherCharge;
    private String cateringCharge;
    private String dynamicFare;
    private String totalFare;
    private String wpServiceCharge;
    private String wpServiceTax;
    private String travelInsuranceCharge;
    private String travelInsuranceServiceTax;
    private String insuredPsgnCount;
    private String nextEnqDate;
    private String preEnqDate;
    private String timeStamp;
    private String otpAuthenticationFlag;
    private String cateringFlag;
    private String totalCollectibleAmount;
    private List<AvlDay> avlDayList;
    private List<InformationMessage> informationMessage;
    private String reTry;
    private String altAvlEnabled;
    private String altTrainEnabled;
    private String altClsEnabled;
    private String taRdsFlag;
    private String upiRdsFlag;
    private String rdsTxnPwdFlag;
    private String ftBookingMsgFlag;
}
