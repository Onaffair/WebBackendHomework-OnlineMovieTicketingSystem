package com.example.onaffair.service;

import com.example.onaffair.entity.MovieActor;
import java.util.List;

public interface MovieActorService {
    List<MovieActor> getAllMovieActors();

    boolean addMovieActor(MovieActor movieActor);

    boolean updateMovieActor(MovieActor movieActor);

    boolean deleteMovieActor(Integer movieId, Integer actorId);

    List<MovieActor> findMovieActorsByCondition(MovieActor movieActor);
}
