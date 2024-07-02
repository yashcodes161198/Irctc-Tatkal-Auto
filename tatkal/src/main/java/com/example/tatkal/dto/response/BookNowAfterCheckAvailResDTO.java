package com.example.tatkal.dto.response;

import com.example.tatkal.dto.request.BkgCfgsDTO;
import com.example.tatkal.dto.request.BoardingStationDTO;

import java.time.LocalDateTime;
import java.util.List;

public class BookNowAfterCheckAvailResDTO {
    private List<BoardingStationDTO> boardingStationList;
    private LocalDateTime timeStamp;
    private String mealChoiceenable;
    private List<BkgCfgsDTO> bkgCfgs;
}






