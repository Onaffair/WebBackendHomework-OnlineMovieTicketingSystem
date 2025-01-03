package com.example.onaffair.service.impl;

import com.example.onaffair.entity.MovieActor;
import com.example.onaffair.mapper.MovieActorMapper;
import com.example.onaffair.service.MovieActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieActorServiceImpl implements MovieActorService {
    @Autowired
    private MovieActorMapper movieActorMapper;

    @Override
    public List<MovieActor> getAllMovieActors() {
        return movieActorMapper.getAll();
    }

    @Override
    public boolean addMovieActor(MovieActor movieActor) {
        return movieActorMapper.insert(movieActor);
    }

    @Override
    public boolean updateMovieActor(MovieActor movieActor) {
        return movieActorMapper.update(movieActor);
    }

    @Override
    public boolean deleteMovieActor(Integer movieId, Integer actorId) {
        return movieActorMapper.delete(movieId, actorId);
    }

    @Override
    public List<MovieActor> findMovieActorsByCondition(MovieActor movieActor) {
        return movieActorMapper.selectByIf(movieActor);
    }
}
