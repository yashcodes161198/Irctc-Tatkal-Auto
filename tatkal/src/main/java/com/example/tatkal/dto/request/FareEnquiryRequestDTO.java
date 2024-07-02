package com.example.tatkal.dto.request;

import lombok.Data;

@Data
public class FareEnquiryRequestDTO {
    private String paymentFlag;
    private boolean concessionBooking;
    private boolean ftBooking;
    private boolean loyaltyRedemptionBooking;
    private String ticketType;
    private String quotaCode;
    private boolean moreThanOneDay;
    private String trainNumber;
    private String fromStnCode;
    private String toStnCode;
    private boolean isLogedinReq;
    private String journeyDate;
    private String classCode;
}
