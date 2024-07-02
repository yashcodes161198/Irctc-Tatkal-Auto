package com.example.tatkal.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailsAfterLoginResDTO {
    private String existenceFlag;
    private String mpOldStatus;
    private String spouseOldStatus;
    private String mpCardExpFlag;
    private String funcType;
    private String mpSpouseFlag;
    private String spouseExistenceFlag;
    private String mpFileExistFlag;
    private String updatePhoto;
    private String cardStatus;
    private String userName;
    private String userId;
    private String firstName;
    private String gender;
    private String dob;
    private String email;
    private String countryId;
    private String mobile;
    private String isdCode;
    private String verified;
    private String emailVerified;
    private String mobileVerified;
    private String userEnableState;
    private MobileAppConfigDTO mobileAppConfigDTO;
    private List<InformationMessage> informationMessage;
    private String rdsFlag;
    private String aadhaarVerifyFlag;
    private String eWalletExpireFlag;
    private String eWalletAadhaarRegisterFlag;
    private String renewFlag;
    private String otpLogin;
    private List<String> rolles;
    private String userPaymentFlag;
    private String timeStamp;
    private String kycAddressDisplayStat;
    private String deactivationReason;
    private String enable;
    private String userIdHash;
    private String aadhaarReverifyFlag;
    private String masterList;
    private String fevJourney;
    private List<SoftBankList> softBankList;
    private String ersTktSendEmailFlag;
    private String ersTktDownloadFlag;
    private List<RatingOptions> ratingOptions;

    // Getters and Setters for all fields
    @Data
    public static class MobileAppConfigDTO {
        private String formFillCheckStartTime;
        private String formFillCheckEndTime;
        private String captchaFillCheckStartTime;
        private String captchaFillCheckEndTime;
        private String minmPsgnInputTime;
        private String minmCaptchaInputTime;
        private String minmPaymentTime;
        private String formFillCheckEnable;
        private String paymentCompletCheckEnable;
        private String gstEnable;

        // Getters and Setters
    }

    @Data
    public static class InformationMessage {
        private String message;
        private String popup;
        private String paramName;

        // Getters and Setters
    }

    @Data
    public static class SoftBankList {
        private String bankId;
        private String bankName;
        private String paymentMode;
        private String enableFlag;
        private String displaySection;
        private String cardInputFlag;
        private String travelAgentFlag;
        private String txnPasswordMandatory;
        private String groupId;
        private String displaySequence;
        private String juspayEnableFlag;

        // Getters and Setters
    }

    @Data
    public static class RatingOptions {
        private String v;
        private String en;
        private String hi;

        // Getters and Setters
    }
}

