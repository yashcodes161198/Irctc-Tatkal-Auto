package com.example.tatkal.service;

import com.example.tatkal.dto.request.IRCTCAuthRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class IRCTCAuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${irctc.token.url}")
    private String irctcTokenUrl;

    public String getAuthToken(IRCTCAuthRequestDTO requestDTO) {
        // HTTP Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headers.set("Accept-Language", "en-US,en;q=0.23");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        headers.set("bmirak", "webbm");
        headers.set("Referer", "https://www.irctc.co.in/nget/train-search");
        headers.set("sec-ch-ua-platform", "\"Windows\"");

        // Convert requestDTO to form-urlencoded string
        String requestBody = convertToFormUrlEncoded(requestDTO);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the API call
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                irctcTokenUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Assuming you want to return the response body as a string
        return responseEntity.getBody();
    }

    private String convertToFormUrlEncoded(IRCTCAuthRequestDTO requestDTO) {
        return "grant_type=" + requestDTO.getGrantType() +
                "&data=" + requestDTO.getData() +
                "&captcha=" + requestDTO.getCaptcha() +
                "&uid=" + requestDTO.getUid() +
                "&otpLogin=" + requestDTO.isOtpLogin() +
                "&lso=" + requestDTO.getLso() +
                "&encodedPwd=" + requestDTO.isEncodedPwd();
    }
}
