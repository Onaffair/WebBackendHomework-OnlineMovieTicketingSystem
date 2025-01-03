package com.example.onaffair.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieActor {
    private Integer movieId;
    private Integer actorId;
    private String role;

}
