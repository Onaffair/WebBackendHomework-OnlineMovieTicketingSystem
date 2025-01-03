package com.example.onaffair.service.impl;

import com.example.onaffair.entity.ScreeningRoom;
import com.example.onaffair.mapper.ScreeningRoomMapper;
import com.example.onaffair.service.ScreeningRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningRoomServiceImpl implements ScreeningRoomService {
    @Autowired
    private ScreeningRoomMapper screeningRoomMapper;

    @Override
    public List<ScreeningRoom> getAllScreeningRooms() {
        return screeningRoomMapper.getAll();
    }

    @Override
    public boolean addScreeningRoom(ScreeningRoom screeningRoom) {
        return screeningRoomMapper.insert(screeningRoom);
    }

    @Override
    public boolean updateScreeningRoom(ScreeningRoom screeningRoom) {
        return screeningRoomMapper.update(screeningRoom);
    }

    @Override
    public boolean deleteScreeningRoom(Integer roomId) {
        return screeningRoomMapper.delete(roomId);
    }

    @Override
    public List<ScreeningRoom> findScreeningRoomsByCondition(ScreeningRoom screeningRoom) {
        return screeningRoomMapper.selectByIf(screeningRoom);
    }
}
