package com.example.onaffair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.onaffair.dto.MovieGetDto;
import com.example.onaffair.entity.Movie;
import com.example.onaffair.mapper.MovieMapper;
import com.example.onaffair.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;


    @Override
    public List<Movie> getAllMovies() {
        return movieMapper.getAll();
    }

    @Override
    public boolean addMovie(Movie movie) {
        return movieMapper.insert(movie) > 0;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieMapper.update(movie) > 0;
    }

    @Override
    public boolean deleteMovie(Integer movieId) {
        return movieMapper.delete(movieId) > 0;
    }

    @Override
    public List<Movie> findMoviesByCondition(Movie movie) {
        return movieMapper.selectByIf(movie);
    }


    @Override
    public List<Movie> getMoviesByPage(MovieGetDto dto) {
        Page<Movie> page = new Page<>(dto.getPage(),dto.getSize());
        Page<Movie> res = movieMapper.selectByPage(page,dto);
        return res.getRecords();
    }

}
