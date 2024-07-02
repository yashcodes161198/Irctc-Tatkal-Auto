//package com.example.tatkal.controller;
//
//import com.example.tatkal.dto.response.BookNowAfterCheckAvailResDTO;
//import com.example.tatkal.dto.response.*;
//import com.example.tatkal.dto.request.AuthTokenRequestDTO;
//import com.example.tatkal.service.TatkalService;
//import net.sourceforge.tess4j.TesseractException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class TatkalController {
//
//    @Autowired
//    private TatkalService tatkalService;
//
//    @GetMapping("/login")
//    public ResponseEntity<CaptchaResponseDTO> login() throws TesseractException, IOException {
//        CaptchaResponseDTO res = tatkalService.login();
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//    @PostMapping("/allInOne")
//    public ResponseEntity<Object> allInOne(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        Object res = tatkalService.allInOne(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//    @PostMapping("/getAuthToken")
//    public ResponseEntity<AuthTokenResponseDTO> getAuthToken(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        AuthTokenResponseDTO res = tatkalService.getAuthToken(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        String res = tatkalService.logout(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//    @PostMapping("/getUserDetailsAfterLogin")
//    public ResponseEntity<UserDetailsAfterLoginResDTO> getUserDetailsAfterLogin(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        UserDetailsAfterLoginResDTO res = tatkalService.getValidUserDetailsAfterLogin(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//    @PostMapping("/searchTicket")
//    public ResponseEntity<UserDetailsAfterLoginResDTO> searchTicket(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//         TrainSearchResDTO res = tatkalService.searchTicket(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//
//    @PostMapping("/checkAvailability")
//    public ResponseEntity<FareEnquiryResponseDTO> checkAvailability(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        FareEnquiryResponseDTO res = tatkalService.checkAvailability(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//
//    @PostMapping("/bookTicketAfterCheckingAvailability")
//    public ResponseEntity<FareEnquiryResponseDTO> bookTicketAfterCheckingAvailability(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        BookNowAfterCheckAvailResDTO res = tatkalService.bookTicketAfterCheckingAvailability(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//
//    @PostMapping("/submitPassengerDetails")
//    public ResponseEntity<FareEnquiryResponseDTO> submitPassengerDetails(@RequestBody AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
//        SubmitPassengerDetailsResDTO res = tatkalService.submitPassengerDetails(authTokenRequestDTO);
//        return new ResponseEntity(res, HttpStatus.OK);
//    }
//
//
//
//}
