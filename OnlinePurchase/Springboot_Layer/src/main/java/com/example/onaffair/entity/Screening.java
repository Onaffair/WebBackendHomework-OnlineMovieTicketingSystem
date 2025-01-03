package com.example.onaffair.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screening {
    private Integer screeningId;
    private Integer movieId;
    private Integer roomId;
    private Date screeningTime;
    private Double price;


}
