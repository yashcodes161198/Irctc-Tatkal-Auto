package com.example.tatkal.dto.request;

import lombok.Data;

@Data
public class TicketSearchReqDTO {

    private boolean concessionBooking;
    private String srcStn;
    private String destStn;
    private String jrnyClass;
    private String jrnyDate;
    private String quotaCode;
    private boolean currentBooking;
    private boolean flexiFlag;
    private boolean handicapFlag;
    private String ticketType;
    private boolean loyaltyRedemptionBooking;
    private boolean ftBooking;

}
