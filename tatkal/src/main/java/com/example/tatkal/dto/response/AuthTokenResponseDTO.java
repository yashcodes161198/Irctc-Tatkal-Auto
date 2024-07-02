package com.example.tatkal.dto.response;

import lombok.Data;

@Data
public class AuthTokenResponseDTO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String message;
}
