package com.example.tatkal.dto.request;

import lombok.Data;

@Data
public class IRCTCAuthRequestDTO {

    private String grantType;
    private String data;
    private String captcha;
    private String uid;
    private boolean otpLogin;
    private String lso;
    private boolean encodedPwd;

}
