package com.example.onaffair.service.impl;

import com.example.onaffair.entity.Screening;
import com.example.onaffair.mapper.ScreeningMapper;
import com.example.onaffair.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    @Autowired
    private ScreeningMapper screeningMapper;

    @Override
    public List<Screening> getAllScreenings() {
        return screeningMapper.getAll();
    }

    @Override
    public boolean addScreening(Screening screening) {
        return screeningMapper.insert(screening);
    }

    @Override
    public boolean updateScreening(Screening screening) {
        return screeningMapper.update(screening);
    }

    @Override
    public boolean deleteScreening(Integer screeningId) {
        return screeningMapper.delete(screeningId);
    }

    @Override
    public List<Screening> findScreeningsByCondition(Screening screening) {
        return screeningMapper.selectByIf(screening);
    }
}
