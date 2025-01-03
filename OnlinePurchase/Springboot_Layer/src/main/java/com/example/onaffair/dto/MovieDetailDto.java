package com.example.onaffair.dto;

import com.example.onaffair.entity.Actor;
import com.example.onaffair.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDetailDto {
    private Movie movie;
    private List<ActorWithRole> actors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActorWithRole {
        private Actor actor;
        private String role;
    }
}
