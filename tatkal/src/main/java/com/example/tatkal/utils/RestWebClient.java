package com.example.tatkal.utils;

import com.example.tatkal.entity.User;
import com.example.tatkal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Objects;

@Service
public class RestWebClient {

    private final RestTemplate restTemplate;

    public RestWebClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Autowired
//    private UserRepo userRepo;

    public <T> ResponseEntity<T> makeRequest(Object requestBody, Class<T> responseBodyClass, Map<String, String> headersMap, String requestUrl, HttpMethod requestType, User user) {
        setHeaders(headersMap, user);
        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<?> entity;
        if (requestBody != null) {
            entity = new HttpEntity<>(requestBody, headers);
        } else {
            entity = new HttpEntity<>(headers);
        }

        ResponseEntity<T> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(requestUrl, requestType, entity, responseBodyClass);
            String csrfToken = Objects.requireNonNull(responseEntity.getHeaders().get("csrf-token")).get(0);
            user.setCsrfToken(csrfToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        userRepo.save(user);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + responseEntity.getBody());
        } else {
            System.out.println("Error: " + responseEntity.getStatusCode());
        }
        return responseEntity;
    }

    private void setHeaders(Map<String, String> headersMap, User user) {
        headersMap.putIfAbsent("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headersMap.putIfAbsent("spa-csrf-token", user.getCsrfToken());
        headersMap.putIfAbsent("Accept-Language", "en-US,en;q=0.0");
        headersMap.putIfAbsent("sec-ch-ua-mobile", "?0");
        headersMap.putIfAbsent("Authorization", "Bearer " + user.getAccessToken());
        headersMap.putIfAbsent("greq", user.getUid());
        headersMap.putIfAbsent("Content-Language", "en");
        headersMap.putIfAbsent("Content-Type", "application/json; charset=UTF-8");
        headersMap.putIfAbsent("Accept", "application/json, text/plain, */*");
        headersMap.putIfAbsent("bmirak", "webbm");
        headersMap.putIfAbsent("Referer", "https://www.irctc.co.in/nget/train-search");
        headersMap.putIfAbsent("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        headersMap.putIfAbsent("bmiyek", user.getUserIdHash());
        headersMap.putIfAbsent("sec-ch-ua-platform", "\"Windows\"");
    }
}

