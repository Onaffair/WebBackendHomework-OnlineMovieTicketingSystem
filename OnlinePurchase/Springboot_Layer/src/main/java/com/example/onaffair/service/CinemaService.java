package com.example.onaffair.service;

import com.example.onaffair.dto.CinemaGetDto;
import com.example.onaffair.entity.Cinema;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CinemaService {
    List<Cinema> getAllCinemas();

    boolean addCinema(Cinema cinema);

    boolean updateCinema(Cinema cinema);

    boolean deleteCinema(Integer cinemaId);

    List<Cinema> findCinemasByCondition(Cinema cinema);

    List<Cinema> getCinemaByPage(CinemaGetDto dto);

}
