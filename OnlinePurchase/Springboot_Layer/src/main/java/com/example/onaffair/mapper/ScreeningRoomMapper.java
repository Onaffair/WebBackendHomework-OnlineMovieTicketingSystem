package com.example.onaffair.mapper;

import com.example.onaffair.entity.ScreeningRoom;
import java.util.List;

public interface ScreeningRoomMapper {
    List<ScreeningRoom> getAll();

    boolean insert(ScreeningRoom screeningRoom);

    boolean update(ScreeningRoom screeningRoom);

    boolean delete(Integer roomId);

    List<ScreeningRoom> selectByIf(ScreeningRoom screeningRoom);
}
