package com.example.tatkal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CaptchaResponseDTO {
    private String captchaType;
    private String captchaQuestion;
    private String captchaTime;
    private String status;
    private String timeStamp;
    private String nlpcaptchEnabled;
    private String captcha;
    private String captchaText;
}

