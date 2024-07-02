package com.example.tatkal.service;

import com.example.tatkal.dto.request.*;
import com.example.tatkal.dto.response.*;
import com.example.tatkal.entity.User;
import com.example.tatkal.repo.UserRepo;
import com.example.tatkal.utils.EncryptDecrypt;
import com.example.tatkal.utils.RestWebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class TatkalService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EncryptDecrypt encryptDecrypt;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private RestWebClient restWebClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${api.baseurl}") // Define base URL in application.properties or application.yml
    private String baseUrl;

    public CaptchaResponseDTO login() throws TesseractException, IOException {
        String loginUrl = "https://www.irctc.co.in/eticketing/protected/mapps1/loginCaptcha";
//        String url = "https://api.example.com/data";
        String url = "https://www.irctc.co.in/eticketing/protected/mapps1/loginCaptcha";

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headers.set("Accept-Language", "en-US,en;q=0.0");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        headers.set("greq", "1719041682798");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Content-Language", "en");
        headers.set("Accept", "application/json, text/plain, */*");
        headers.set("bmirak", "webbm");
        headers.set("Referer", "https://www.irctc.co.in/nget/train-search");
        headers.set("sec-ch-ua-platform", "\"Windows\"");

        // Combine headers into a request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);


        // Make the request
        ResponseEntity<CaptchaResponseDTO> captchaResponseDTOResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CaptchaResponseDTO.class);
        CaptchaResponseDTO captchaResponseDTO = captchaResponseDTOResponseEntity.getBody();
//        captchaResponseDTO.setCaptchaQuestion("iVBORw0KGgoAAAANSUhEUgAAAMsAAAAyCAYAAADyZi/iAAAFTUlEQVR42u1ccURdURifZ56ZiSSTSSSTzIwkmZnHM5mZGZlJJmMymUkkySRjsj+S/pnJzMyYTJIZk0x/TMxkJjMmk8yMJEkSb+fYmW6f7957zr3nvnvve78fR3r3nO+797vf7557vu8798gRAAAAAAAAAAAAAAAAAAAAACg/FAqFjGhdor0W7adoO6LtFxyAlYAoHZDDTFg5Qft4yG8U7VvBBwbXaAVEzxI5PGjpHg0RuUvw3OSQRaItKWQRXU+omaSQcLLcIYd/WLpHP4jcHnhussjyMUFkoU/WVdHyomVDXqNtshwXbY90uRDy/lwk8nblwwOemyyySFxPCFkWyNBcDHYaZuwzxfSbIn2ehdT7zE8nkAyyfJeL6gSQZZsMzRTZRpcY23wS7RjT9zzptxd0JlCvn1ZnKsAuWejN6U0AWWKLeAl1taL9Iae+IX/3GEMDEXcD6r5LH17w2GSRZYz8/0vnyRgxWQpxkEWuidQMQpH3GddPZ6GA+qnugRDX0q5e6b44Qu7y74r6PQ82mJOlSrQt8tuDYpMl7GLbkm2eMmp0bFFNZ0OBc4a6z5LxUt7JANeQU6/TOlgW7QxYYeDkTPRJrhlqyoksQlw3o2LWYPwsGTthqH88qG6HjL4AZtyKI4CSZrIcE22dHJosF7LIWUC9ohzKmYhWafjaQ9c5RzXHHhVtk4y/bHgNPczMNKlmmqzjNbNDtM/MudaDHZoOzCTYpLFPl/qaRRKCSYBK4pw1lCNLc9aInC7NsZ1k3LpJBFBVOzgDNTJA0eJzrq+IzjmwQ58sGSaqM1MGZHnLTFqdAWWNEjkLmuPmybiHhnpnTfNSapah9xvrF11HlElJxnFaSpUsLonHyRDyGhh59T5j6pgxDQY668nYdyFe3UbBEANHlGUvOmUwaSeLS+JxSXed4SF3wcQBxfGRILORR9j6psHYWjL2PRhiRpY2xomulhJZXBKPv4OEahnZt4jcNZ/+dL3UbahvmoxvDOEPm2CIuZPPkW4rpUIWj8RjzpJ8LrLV7tI3z4Rxs4b6VpnCS7/mhl0wxNzJmxhD9pQIWbjEY79lHRM6ORO1qS1wbkbJ2Imqqhpk0TQOU037y1lImEayuCQepyOw8TkmDF9N+lSFzfpztgJZ4iFLLVNkOZxWsigH3mWqrE9EZGea+Osjx++T418C6tmLs0IbZDnoO8aUwVSljSwuicdtr6SrBZ29Xus+VeDoxL2AejbCLPABe2SpZG7GeArJwiUeOyK2M7c35bw61spsk6gIqIcmJG/Ay2Mgi+o/wLx/N6SFLLJqmCHKeJFs/Zzb9Sj+PiG/vwyhYyTqNRjIok+WLFPz9DoNZGGKGyU+FNHW3H76SmYnaC6EjjPMLFUHT4+BLGrMbRtfWSkmWVSAYoOJ6J0ssr3pl1pohn/Vgo75MFUAgGVHFF2/poUsajZcZl4f22Kw95DPc2bYgo4mJtL3RjfSp2bAebDDngNfSRFZpphTvBeTvWuYfIqTwKcs6bnFyJel/oOiNf8PKavq8lOiXRPtsXPmAzssOiJTZJlUskSKAOcz5yLqneV7e9uDmEhIFpksrSHIsl/GZLnmIqojgvsrF/yLBpezrwI2rWCHfUec0XEgNd0f2n1YxmTJqOBCgexmzER4n5tVUvm9KtDcV5GybbUV4YXMyZhsnQaiu1kVtAQeVgEAvYDADKwCADxZHmHLKgD4E4VLCDbDMgBwQJJqFb6k3yBbhHUA4B9JvLaqbkRZCg8ApUKWFdOP1QFAqZNlSxHm/1faX6pvkGHXHgAAAAAAAAAAAAAAABAefwGHTKAVAON8iwAAAABJRU5ErkJggg==");
        String captchaText = captchaService.extractCaptchaText(captchaResponseDTOResponseEntity.getBody());
//        realCaptchaString = NJfZVe
        captchaResponseDTO.setCaptchaText(captchaText);
        persistUserDetails(captchaResponseDTO);
        return captchaResponseDTO;
    }

    private void persistUserDetails(CaptchaResponseDTO captchaResponseDTO) {
        User user = new User();
        user.setUserName("kumar22096");
        user.setPassword("Kumar@123");
        user.setUid(captchaResponseDTO.getStatus());
        user.setCaptcha(captchaResponseDTO.getCaptchaText());
        User existingUser = userRepo.findByUserName(user.getUserName());
        if(existingUser != null){
            existingUser.setUid(captchaResponseDTO.getStatus());
            userRepo.save(existingUser);
        } else{
            userRepo.save(user);
        }
    }

    public AuthTokenResponseDTO getAuthToken(AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());
        String data = encryptDecrypt.getDataForLogin(authTokenRequestDTO);
        IRCTCAuthRequestDTO irctcAuthRequestDTO = new IRCTCAuthRequestDTO();
        irctcAuthRequestDTO.setData(data);
        irctcAuthRequestDTO.setCaptcha(authTokenRequestDTO.getCaptcha());
        irctcAuthRequestDTO.setUid(user.getUid());
        irctcAuthRequestDTO.setLso("");
        irctcAuthRequestDTO.setGrantType("password");
        irctcAuthRequestDTO.setEncodedPwd(true);
        irctcAuthRequestDTO.setOtpLogin(false);


        String url = "https://www.irctc.co.in/authprovider/webtoken";

        HttpHeaders headers = new HttpHeaders();
        headers.set("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headers.set("Accept-Language", "en-US,en;q=0.6");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "application/json, text/plain, */*");
        headers.set("bmirak", "webbm");
        headers.set("Referer", "https://www.irctc.co.in/nget/train-search");
        headers.set("sec-ch-ua-platform", "\"Windows\"");

        // Convert the RequestDTO to a map of parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", irctcAuthRequestDTO.getGrantType());
        params.add("data", irctcAuthRequestDTO.getData());
        params.add("captcha", irctcAuthRequestDTO.getCaptcha());
        params.add("uid", irctcAuthRequestDTO.getUid());
        params.add("otpLogin", String.valueOf(irctcAuthRequestDTO.isOtpLogin()));
        params.add("lso", irctcAuthRequestDTO.getLso());
        params.add("encodedPwd", String.valueOf(irctcAuthRequestDTO.isEncodedPwd()));

//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        AuthTokenResponseDTO authTokenResponseDTO = restTemplate.postForObject(url, entity, AuthTokenResponseDTO.class);
        user.setAccessToken(authTokenResponseDTO.getAccess_token());
        user.setRefreshToken(authTokenResponseDTO.getRefresh_token());
        user.setCaptcha(authTokenRequestDTO.getCaptcha());
        userRepo.save(user);
        return authTokenResponseDTO;
    }

    public UserDetailsAfterLoginResDTO getValidUserDetailsAfterLogin(AuthTokenRequestDTO authTokenRequestDTO){
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());

        String requestUrl = "https://www.irctc.co.in/eticketing/protected/mapps1/validateUser?source=3";

        Map<String, String> headersMap = new HashMap<>();

        String spaCsrfToken = String.valueOf(System.currentTimeMillis());
        user.setCsrfToken(spaCsrfToken);

        headersMap.put("Content-Type", "application/x-www-form-urlencoded");

        ResponseEntity<UserDetailsAfterLoginResDTO> responseEntity = restWebClient.makeRequest(null, UserDetailsAfterLoginResDTO.class, headersMap, requestUrl, HttpMethod.GET, user);

        // Process the response as needed
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + responseEntity.getBody());
        } else {
            System.out.println("Error: " + responseEntity.getStatusCode());
        }
        user.setUserIdHash(responseEntity.getBody().getUserIdHash());
        userRepo.save(user);
        return responseEntity.getBody();
    }


    public String logout(AuthTokenRequestDTO authTokenRequestDTO) {
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json, text/plain, */*");
        headers.set("Accept-Language", "en-US,en;q=0.0");
        headers.set("Authorization", "Bearer " + user.getAccessToken());
        headers.set("Connection", "keep-alive");
        headers.set("Content-Language", "en");
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Cookie", "TS018d84e5=01d83d9ce762cb59425e90feb67f9a59c2d4cb14e342a226ca31fdc9e24dc46b157b260e83f4aae2c427eb45171500decf1dff0ea3; JSESSIONID=vaxApRM9S8kzgDfShTUtKrXpeiDjjlPVZZSQXKRG7FMwcUUOVnML!831807575; et_appVIP1=721571338.16671.0000; JSESSIONID=NGpAAROK_w6hRwZFOO09fdUcVTgChIYPOAIZORiA0VjYsgD0NhqJ!-1381525415; TS018d84e5=01d83d9ce797391921124f91cafa448585213d6d0f4dc54e5d377b13430d1f475675ade68b03783c0f601e33f135880a25cc66ea76; et_appVIP1=721571338.16671.0000");
        headers.set("Origin", "https://www.irctc.co.in");
        headers.set("Referer", "https://www.irctc.co.in/nget/train-search");
        headers.set("Sec-Fetch-Dest", "empty");
        headers.set("Sec-Fetch-Mode", "cors");
        headers.set("Sec-Fetch-Site", "same-origin");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        headers.set("bmirak", "webbm");
        headers.set("bmiyek", "5B6B056EB27180A3DFAE2BD86FF19BC8");
        headers.set("greq", "GQ:26bb3278-1931-4ffe-8766-c702b8016da1");
        headers.set("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("sec-ch-ua-platform", "Windows");
        headers.set("spa-csrf-token", "1719071478833--683600323");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Make the HTTP POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://www.irctc.co.in/eticketing/protected/mapps1/logout",
                HttpMethod.POST,
                requestEntity,
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Logout successful");
            return "Logout successful";
        } else {
            System.out.println("Logout failed with status code: " + responseEntity.getStatusCode());
            return "Logout failed with status code: " + responseEntity.getStatusCode();
        }
    }

    public TrainSearchResDTO searchTicket(AuthTokenRequestDTO authTokenRequestDTO) {
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());
        String requestUrl = "https://www.irctc.co.in/eticketing/protected/mapps1/altAvlEnq/TC";

        Map<String, String> headersMap = new HashMap<>();

        TicketSearchReqDTO ticketSearchReqDTO = new TicketSearchReqDTO();
        ticketSearchReqDTO.setFtBooking(false);
        ticketSearchReqDTO.setConcessionBooking(false);
        ticketSearchReqDTO.setSrcStn("PNBE");
        ticketSearchReqDTO.setDestStn("NDLS");
        ticketSearchReqDTO.setJrnyClass("3A");
        ticketSearchReqDTO.setJrnyDate("20240626");
        ticketSearchReqDTO.setQuotaCode("TQ");
        ticketSearchReqDTO.setFlexiFlag(false);
        ticketSearchReqDTO.setHandicapFlag(false);
        ticketSearchReqDTO.setTicketType("E");
        ticketSearchReqDTO.setLoyaltyRedemptionBooking(false);
        ticketSearchReqDTO.setFtBooking(false);

        ResponseEntity<TrainSearchResDTO> responseEntity = restWebClient.makeRequest(ticketSearchReqDTO, TrainSearchResDTO.class, headersMap, requestUrl, HttpMethod.POST, user);

        // Process the response as needed
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + responseEntity.getBody());
        } else {
            System.out.println("Error: " + responseEntity.getStatusCode());
        }
        userRepo.save(user);
        return responseEntity.getBody();
    }

    public Object allInOne(AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
        this.getAuthToken(authTokenRequestDTO);
        return this.getValidUserDetailsAfterLogin(authTokenRequestDTO);

//        return this.searchTicket(authTokenRequestDTO);

//        return obj;
    }

    public FareEnquiryResponseDTO checkAvailability(AuthTokenRequestDTO authTokenRequestDTO) {
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());

        Map<String, String> headersMap = new HashMap<>();
//        headersMap.put("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
//        headersMap.put("spa-csrf-token", "1719294793383-1576646297");
//        headersMap.put("Accept-Language", "en-US,en;q=0.0");
//        headersMap.put("sec-ch-ua-mobile", "?0");
//        headersMap.put("Authorization", "Bearer cacc814f-8acb-4110-8201-db2883f3d22d");
//        headersMap.put("greq", "GQ:5e34c790-71a2-492e-994a-0d82f7b2bd63");
//        headersMap.put("Content-Language", "en");
//        headersMap.put("Content-Type", "application/json; charset=UTF-8");
//        headersMap.put("Accept", "application/json, text/plain, */*");
//        headersMap.put("bmirak", "webbm");
//        headersMap.put("Referer", "https://www.irctc.co.in/nget/booking/train-list");
//        headersMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
//        headersMap.put("bmiyek", "5B6B056EB27180A3DFAE2BD86FF19BC8");
//        headersMap.put("sec-ch-ua-platform", "\"Windows\"");
//        headersMap.put("Cookie", "JSESSIONID=PHpN6GcfNqGAgXVGd0WiHdU7kJYwW2Ba08vSTWxSAcBg3lHsteuJ!831807575; TS018d84e5=01d83d9ce777a081eb4fbb87a44cb5b397c28d2e6420c2369ddb1a3e274c7e56df1958752cff188751fe27390801b11707350416ea; et_appVIP1=721571338.16671.0000");

        String requestUrl = "https://www.irctc.co.in/eticketing/protected/mapps1/avlFarenquiry/12393/20240626/PNBE/NDLS/3A/TQ/N";

        FareEnquiryRequestDTO requestBody = new FareEnquiryRequestDTO();
        requestBody.setPaymentFlag("N");
        requestBody.setConcessionBooking(false);
        requestBody.setFtBooking(false);
        requestBody.setLoyaltyRedemptionBooking(false);
        requestBody.setTicketType("E");
        requestBody.setQuotaCode("TQ");
        requestBody.setMoreThanOneDay(true);
        requestBody.setTrainNumber("12393");
        requestBody.setFromStnCode("PNBE");
        requestBody.setToStnCode("NDLS");
        requestBody.setLogedinReq(true);
        requestBody.setJourneyDate("20240627");
        requestBody.setClassCode("3A");

        ResponseEntity<FareEnquiryResponseDTO> responseEntity = restWebClient.makeRequest(requestBody, FareEnquiryResponseDTO.class, headersMap, requestUrl, HttpMethod.POST, user);

//        ResponseEntity<String> responseEntity = restWebClient.makeRequest(headersMap, requestUrl, requestBody, user);

        // Process the response as needed
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + responseEntity.getBody());
        } else {
            System.out.println("Error: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

    public BookNowAfterCheckAvailResDTO bookTicketAfterCheckingAvailability(AuthTokenRequestDTO authTokenRequestDTO) {
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());

        Map<String, String> headersMap = new HashMap<>();

        String requestUrl = "https://www.irctc.co.in/eticketing/protected/mapps1/boardingStationEnq";

        BookNowAfterCheckAvailReqDTO reqDTO = new BookNowAfterCheckAvailReqDTO();
        reqDTO.setClusterFlag("N");
        reqDTO.setOnwardFlag("N");
        reqDTO.setCod(false);
        reqDTO.setReservationMode("WS_TA_B2C");
        reqDTO.setAutoUpgradationSelected(false);
        reqDTO.setGnToCkOpted(false);
        reqDTO.setPaymentType(1);
        reqDTO.setTwoPhaseAuthRequired(false);
        reqDTO.setCaptureAddress(0);

        AlternateAvlInputDTO alternateAvlInputDTO = new AlternateAvlInputDTO();
        alternateAvlInputDTO.setTrainNo("12393");
        alternateAvlInputDTO.setDestStn("NDLS");
        alternateAvlInputDTO.setSrcStn("PNBE");
        alternateAvlInputDTO.setJrnyDate("20240627");
        alternateAvlInputDTO.setQuotaCode("GN");
        alternateAvlInputDTO.setJrnyClass("3A");
        alternateAvlInputDTO.setConcessionPassengers(false);

        reqDTO.setAlternateAvlInputDTO(Collections.singletonList(alternateAvlInputDTO));
        reqDTO.setPassBooking(false);
        reqDTO.setJournalistBooking(false);

        ResponseEntity<BookNowAfterCheckAvailResDTO> responseEntity = restWebClient.makeRequest(reqDTO, BookNowAfterCheckAvailResDTO.class, headersMap, requestUrl, HttpMethod.POST, user);

        return responseEntity.getBody();
    }

    public SubmitPassengerDetailsResDTO submitPassengerDetails(AuthTokenRequestDTO authTokenRequestDTO) {
        User user = userRepo.findByUserName(authTokenRequestDTO.getUserName());

        Map<String, String> headersMap = new HashMap<>();

        String requestUrl = "https://www.irctc.co.in/eticketing/protected/mapps1/allLapAvlFareEnq/Y";

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
//        headers.add("spa-csrf-token", "1719346152920--1264627086");
//        headers.add("Accept-Language", "en-US,en;q=0.0");
//        headers.add("sec-ch-ua-mobile", "?0");
//        headers.add("Authorization", "Bearer 43c7368c-54e5-410b-b4e0-6fe12ad7795f");
//        headers.add("greq", "GQ:0d252595-5a15-473d-83a2-863da48be018");
//        headers.add("Content-Language", "en");
//        headers.add("Content-Type", "application/json; charset=UTF-8");
//        headers.add("Accept", "application/json, text/plain, */*");
//        headers.add("bmirak", "webbm");
//        headers.add("Referer", "https://www.irctc.co.in/nget/booking/psgninput");
//        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
//        headers.add("bmiyek", "5B6B056EB27180A3DFAE2BD86FF19BC8");
//        headers.add("sec-ch-ua-platform", "\"Windows\"");

//        HttpEntity<ReqDTO> entity = new HttpEntity<>(reqDTO, headers);
        PassengerDetailsReqDTO passengerDetailsReqDTO = this.generatePassengerDetailsReqDTO();
        ResponseEntity<SubmitPassengerDetailsResDTO> responseEntity = restWebClient.makeRequest(passengerDetailsReqDTO, SubmitPassengerDetailsResDTO.class, headersMap, requestUrl, HttpMethod.POST, user);
        return responseEntity.getBody();

    }

    public PassengerDetailsReqDTO generatePassengerDetailsReqDTO() {
        PassengerDetailsReqDTO reqDTO = new PassengerDetailsReqDTO();
        reqDTO.setClusterFlag("N");
        reqDTO.setOnwardFlag("N");
        reqDTO.setCod(false);
        reqDTO.setReservationMode("WS_TA_B2C");
        reqDTO.setAutoUpgradationSelected(false);
        reqDTO.setGnToCkOpted(false);
        reqDTO.setPaymentType("2");
        reqDTO.setTwoPhaseAuthRequired(false);
        reqDTO.setCaptureAddress(0);
        reqDTO.setWsUserLogin("kumar22096");
        reqDTO.setMoreThanOneDay(false);
        reqDTO.setClientTransactionId("lxuub1iy");
        reqDTO.setBoardingStation("PNBE");
        reqDTO.setReservationUptoStation("NDLS");
        reqDTO.setMobileNumber("8935855059");
        reqDTO.setTicketType("E");
        reqDTO.setMainJourneyTxnId(null);
        reqDTO.setMainJourneyPnr("");
        reqDTO.setCaptcha("");
        reqDTO.setGeneralistChildConfirm(false);
        reqDTO.setFtBooking(false);
        reqDTO.setLoyaltyRedemptionBooking(false);
        reqDTO.setNosbBooking(false);
        reqDTO.setWarrentType(0);
        reqDTO.setFtTnCAgree(false);
        reqDTO.setBookingChoice(1);
        reqDTO.setBookingConfirmChoice(1);
        reqDTO.setBookOnlyIfCnf(false);
        reqDTO.setReturnJourney(null);
        reqDTO.setConnectingJourney(false);

        LapAvlRequestDTO lapAvlRequestDTO = new LapAvlRequestDTO();
        lapAvlRequestDTO.setTrainNo("12393");
        lapAvlRequestDTO.setJourneyDate("20240627");
        lapAvlRequestDTO.setFromStation("PNBE");
        lapAvlRequestDTO.setToStation("NDLS");
        lapAvlRequestDTO.setJourneyClass("3A");
        lapAvlRequestDTO.setQuota("GN");
        lapAvlRequestDTO.setCoachId(null);
        lapAvlRequestDTO.setReservationChoice("99");
        lapAvlRequestDTO.setIgnoreChoiceIfWl(true);
        lapAvlRequestDTO.setTravelInsuranceOpted(false);
        lapAvlRequestDTO.setWarrentType(0);
        lapAvlRequestDTO.setCoachPreferred(false);
        lapAvlRequestDTO.setAutoUpgradation(false);
        lapAvlRequestDTO.setBookOnlyIfCnf(false);
        lapAvlRequestDTO.setAddMealInput(null);
        lapAvlRequestDTO.setConcessionBooking(false);
        lapAvlRequestDTO.setSsQuotaSplitCoach("N");

        Passenger passenger1 = new Passenger();
        passenger1.setPassengerName("abc kumar");
        passenger1.setPassengerAge(26);
        passenger1.setPassengerGender("M");
        passenger1.setPassengerBerthChoice("LB");
        passenger1.setPassengerFoodChoice(null);
        passenger1.setPassengerBedrollChoice(null);
        passenger1.setPassengerNationality("IN");
        passenger1.setPassengerCardTypeMaster(null);
        passenger1.setPassengerCardNumberMaster(null);
        passenger1.setPsgnConcType(null);
        passenger1.setPsgnConcCardId(null);
        passenger1.setPsgnConcDOB(null);
        passenger1.setPsgnConcCardExpiryDate(null);
        passenger1.setPsgnConcDOBP(null);
        passenger1.setSoftMemberId(null);
        passenger1.setSoftMemberFlag(null);
        passenger1.setPsgnConcCardExpiryDateP(null);
        passenger1.setPassengerVerified(false);
        passenger1.setMasterPsgnId(null);
        passenger1.setMpMemberFlag(null);
        passenger1.setPassengerForceNumber(null);
        passenger1.setPassConcessionType("0");
        passenger1.setPassUPN(null);
        passenger1.setPassBookingCode(null);
        passenger1.setPassengerSerialNumber(1);
        passenger1.setChildBerthFlag(true);
        passenger1.setPassengerCardType("NULL_IDCARD");
        passenger1.setPassengerIcardFlag(false);
        passenger1.setPassengerCardNumber(null);

        Passenger passenger2 = new Passenger();
        passenger2.setPassengerName("munna tripathi");
        passenger2.setPassengerAge(28);
        passenger2.setPassengerGender("M");
        passenger2.setPassengerBerthChoice("MB");
        passenger2.setPassengerFoodChoice(null);
        passenger2.setPassengerBedrollChoice(null);
        passenger2.setPassengerNationality("IN");
        passenger2.setPassengerCardTypeMaster(null);
        passenger2.setPassengerCardNumberMaster(null);
        passenger2.setPsgnConcType(null);
        passenger2.setPsgnConcCardId(null);
        passenger2.setPsgnConcDOB(null);
        passenger2.setPsgnConcCardExpiryDate(null);
        passenger2.setPsgnConcDOBP(null);
        passenger2.setSoftMemberId(null);
        passenger2.setSoftMemberFlag(null);
        passenger2.setPsgnConcCardExpiryDateP(null);
        passenger2.setPassengerVerified(false);
        passenger2.setMasterPsgnId(null);
        passenger2.setMpMemberFlag(null);
        passenger2.setPassengerForceNumber(null);
        passenger2.setPassConcessionType("0");
        passenger2.setPassUPN(null);
        passenger2.setPassBookingCode(null);
        passenger2.setPassengerSerialNumber(2);
        passenger2.setChildBerthFlag(true);
        passenger2.setPassengerCardType("NULL_IDCARD");
        passenger2.setPassengerIcardFlag(false);
        passenger2.setPassengerCardNumber(null);

        List<Passenger> passengerList = Arrays.asList(passenger1, passenger2);
        lapAvlRequestDTO.setPassengerList(passengerList);

        List<LapAvlRequestDTO> lapAvlRequestDTOList = Arrays.asList(lapAvlRequestDTO);
        reqDTO.setLapAvlRequestDTO(lapAvlRequestDTOList);

        GstDetails gstDetails = new GstDetails();
        gstDetails.setGstIn("");
        gstDetails.setError(null);

        reqDTO.setGstDetails(gstDetails);

        return reqDTO;
    }
}
