package com.example.tatkal.dto.response;

import lombok.Data;

@Data
public class BankDetailDTO {
    private String bankId;
    private String bankName;
    private String paymentMode;
    private String message;
    private String enableFlag;
    private String displaySection;
    private String cardInputFlag;
    private String travelAgentFlag;
    private String txnPasswordMandatory;
    private String groupId;
    private String displaySequence;
    private String juspayEnableFlag;
}
