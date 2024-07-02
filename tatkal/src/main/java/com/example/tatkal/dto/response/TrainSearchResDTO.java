package com.example.tatkal.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrainSearchResDTO {
    private List<TrainDTO> trainBtwnStnsList;
    private List<String> quotaList;
    private LocalDateTime timeStamp;
    private String vikalpInSpecialTrainsAccomFlag;
    private String oneStopJourny;
    private String serveyFlag;
    private String alternateEnquiryFlag;



}
