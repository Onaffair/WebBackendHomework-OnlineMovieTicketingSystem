package com.example.onaffair.controller;

import com.example.onaffair.dto.MovieDetailDto;
import com.example.onaffair.dto.MovieGetDto;
import com.example.onaffair.entity.Actor;
import com.example.onaffair.entity.Movie;
import com.example.onaffair.entity.MovieActor;
import com.example.onaffair.service.ActorService;
import com.example.onaffair.service.MovieActorService;
import com.example.onaffair.service.MovieService;
import com.example.onaffair.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieActorService movieActorService;
    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    public Result<List<Movie>> getMovieList(@RequestParam String bar,
                                      @RequestParam(defaultValue = "hotScreening") String type,
                                      @RequestParam(defaultValue = "全部") String region,
                                      @RequestParam(defaultValue = "全部") String year,
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "30") Integer size){
        MovieGetDto movieGetDto = new MovieGetDto(bar,type,region,year,page,size);
        if (year.equals("全部")) movieGetDto.setYear(null);
        if (type.equals("全部")) movieGetDto.setType(null);
        if (region.equals("全部")) movieGetDto.setRegion(null);

        List<Movie> res = movieService.getMoviesByPage(movieGetDto);

        return new Result<>(Result.success().getCode(), "请求成功", res);
    }
    @GetMapping("/getMv")
    public Result<MovieDetailDto> getMv(@RequestParam(defaultValue = "null") Integer movieId){
        if (movieId == null) return Result.error(400, "请求参数错误");

        Movie movie = new Movie(){{
            setMovieId(movieId);
        }};
        movie = movieService.findMoviesByCondition(movie).get(0);

        List<MovieActor> movieActors =
                movieActorService.findMovieActorsByCondition(new
                        MovieActor(movieId, null, null));

        List<MovieDetailDto.ActorWithRole> actors = new ArrayList<>();
        for (MovieActor movieActor : movieActors) {
            Actor ac = actorService.selectByIf(new Actor(movieActor.getActorId(), null)).get(0);
            actors.add(new MovieDetailDto.ActorWithRole(ac, movieActor.getRole()));
        }
        MovieDetailDto res = new MovieDetailDto(movie, actors);


        return new Result<>(Result.success().getCode(), "请求成功", res);
    }
}
