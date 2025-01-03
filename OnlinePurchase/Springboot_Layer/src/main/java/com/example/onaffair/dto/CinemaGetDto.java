package com.example.onaffair.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaGetDto {
    private Integer movieId;
    private String brand;
    private Integer page,size;
}
