package com.example.tatkal.dto.request;

import lombok.Data;

@Data
public class AuthTokenRequestDTO {
    private String userName;
    private String captcha;
}
