package com.example.onaffair.mapper;

import com.example.onaffair.entity.Seat;
import java.util.List;

public interface SeatMapper {
    List<Seat> getAll();

    boolean insert(Seat seat);

    boolean update(Seat seat);

    boolean delete(Integer seatId,Integer screeningId);

    List<Seat> selectByIf(Seat seat);
}
