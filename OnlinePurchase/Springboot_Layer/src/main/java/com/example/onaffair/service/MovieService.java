package com.example.onaffair.service;

import com.example.onaffair.dto.MovieGetDto;
import com.example.onaffair.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> getAllMovies();


    boolean addMovie(Movie movie);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(Integer movieId);

    List<Movie> findMoviesByCondition(Movie movie);

    List<Movie> getMoviesByPage(MovieGetDto dto);



}
