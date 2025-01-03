package com.example.onaffair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.onaffair.dto.MovieGetDto;
import com.example.onaffair.entity.Movie;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MovieMapper extends BaseMapper<Movie>{
    List<Movie> getAll();

    int insert(Movie movie);

    int update(Movie movie);

    int delete(Integer movieId);

    List<Movie> selectByIf(Movie movie);

    Page<Movie> selectByPage(Page<Movie> page, @Param("dto") MovieGetDto dto);

}
