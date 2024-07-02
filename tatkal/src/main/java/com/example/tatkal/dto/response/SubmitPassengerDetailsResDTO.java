package com.example.tatkal.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SubmitPassengerDetailsResDTO {
    private List<AvlFareResponseDTO> avlFareResponseDTO;
    private String totalCollectibleAmount;
    private List<BankDetailDTO> bankDetailDTO;
    private CaptchaResponseDTO captchaDto;
    private String zeroFareBooking;
}
