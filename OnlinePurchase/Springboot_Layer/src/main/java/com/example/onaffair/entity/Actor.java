package com.example.onaffair.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    private Integer actorId;
    private String actorName;
}
