package com.example.onaffair.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Integer movieId;
    private String title;
    private String synopsis;
    private Date releaseDate;
    private String director;
    private Double rating;
    private String posterUrl;
    private String productionRegion;
    private String tags;
    private Integer collectionCount;
}
