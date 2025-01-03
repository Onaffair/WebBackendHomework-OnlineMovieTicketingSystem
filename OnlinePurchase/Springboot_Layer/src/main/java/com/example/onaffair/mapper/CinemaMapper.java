package com.example.onaffair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.onaffair.dto.CinemaDetailDto;
import com.example.onaffair.dto.CinemaGetDto;
import com.example.onaffair.entity.Cinema;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CinemaMapper extends BaseMapper<Cinema> {
    List<Cinema> getAll();

    int insert(Cinema cinema);

    boolean update(Cinema cinema);

    boolean delete(Integer cinemaId);

    List<Cinema> selectByIf(Cinema cinema);

    Page<Cinema> selectByPage(Page<Cinema> page, @Param("dto") CinemaGetDto dto);

}
