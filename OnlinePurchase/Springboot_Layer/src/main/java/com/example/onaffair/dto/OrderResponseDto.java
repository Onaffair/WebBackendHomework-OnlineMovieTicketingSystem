package com.example.onaffair.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Integer orderId;
    private String cinemaName;
    private String movieName;
    private Double price;
    private String roomNumber;
    private Integer seatId;
    private Date screeningTime;
}
