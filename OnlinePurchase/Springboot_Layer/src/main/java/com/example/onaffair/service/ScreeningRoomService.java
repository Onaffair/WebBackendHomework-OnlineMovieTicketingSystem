package com.example.onaffair.service;

import com.example.onaffair.entity.ScreeningRoom;
import java.util.List;

public interface ScreeningRoomService {
    List<ScreeningRoom> getAllScreeningRooms();

    boolean addScreeningRoom(ScreeningRoom screeningRoom);

    boolean updateScreeningRoom(ScreeningRoom screeningRoom);

    boolean deleteScreeningRoom(Integer roomId);

    List<ScreeningRoom> findScreeningRoomsByCondition(ScreeningRoom screeningRoom);
}
