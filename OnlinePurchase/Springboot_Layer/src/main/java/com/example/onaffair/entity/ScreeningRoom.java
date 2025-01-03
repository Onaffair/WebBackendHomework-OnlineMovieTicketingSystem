package com.example.onaffair.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningRoom {
    private Integer roomId;
    private Integer cinemaId;
    private String roomNumber;
    private Integer seatCount;


}
