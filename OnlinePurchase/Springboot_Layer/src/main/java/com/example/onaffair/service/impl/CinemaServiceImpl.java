package com.example.onaffair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.onaffair.dto.CinemaGetDto;
import com.example.onaffair.entity.Cinema;
import com.example.onaffair.mapper.CinemaMapper;
import com.example.onaffair.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaMapper.getAll();
    }

    @Override
    public boolean addCinema(Cinema cinema) {
        return cinemaMapper.insert(cinema) > 0;
    }

    @Override
    public boolean updateCinema(Cinema cinema) {
        return cinemaMapper.update(cinema);
    }

    @Override
    public boolean deleteCinema(Integer cinemaId) {
        return cinemaMapper.delete(cinemaId);
    }

    @Override
    public List<Cinema> findCinemasByCondition(Cinema cinema) {
        return cinemaMapper.selectByIf(cinema);
    }

    @Override
    public List<Cinema> getCinemaByPage(CinemaGetDto dto) {
        Page<Cinema> page = new Page<>(dto.getPage(),dto.getSize());
        Page<Cinema> res = cinemaMapper.selectByPage(page,dto);
        return res.getRecords();
    }

}
