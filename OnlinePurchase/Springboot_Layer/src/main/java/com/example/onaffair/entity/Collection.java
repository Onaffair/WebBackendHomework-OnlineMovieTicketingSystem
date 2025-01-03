package com.example.onaffair.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    private Integer userId;
    private Integer movieId;
    private Date collectionDate;
}

