package com.example.onaffair.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponseDto {
    private Integer movieId;
    private String movieName;
    private Double rating;
    private String posterUrl;
    private Integer collectionCount;
}
