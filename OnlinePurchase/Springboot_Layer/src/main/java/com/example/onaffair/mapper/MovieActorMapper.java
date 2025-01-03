package com.example.onaffair.mapper;

import com.example.onaffair.entity.MovieActor;
import java.util.List;

public interface MovieActorMapper {
    List<MovieActor> getAll();

    boolean insert(MovieActor movieActor);

    boolean update(MovieActor movieActor);

    boolean delete(Integer movieId, Integer actorId);

    List<MovieActor> selectByIf(MovieActor movieActor);
}
