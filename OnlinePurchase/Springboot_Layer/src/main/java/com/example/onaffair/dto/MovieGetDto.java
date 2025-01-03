package com.example.onaffair.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGetDto {
    String bar,type,region,year;
    Integer page,size;
}
