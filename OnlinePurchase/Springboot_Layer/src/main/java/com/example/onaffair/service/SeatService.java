package com.example.onaffair.service;

import com.example.onaffair.entity.Seat;
import java.util.List;

public interface SeatService {
    List<Seat> getAllSeats();

    boolean addSeat(Seat seat);

    boolean updateSeat(Seat seat);

    boolean deleteSeat(Integer seatId, Integer screeningId);

    List<Seat> findSeatsByCondition(Seat seat);
}
