package com.example.onaffair.service.impl;

import com.example.onaffair.entity.Seat;
import com.example.onaffair.mapper.SeatMapper;
import com.example.onaffair.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatMapper seatMapper;

    @Override
    public List<Seat> getAllSeats() {
        return seatMapper.getAll();
    }

    @Override
    public boolean addSeat(Seat seat) {
        return seatMapper.insert(seat);
    }

    @Override
    public boolean updateSeat(Seat seat) {
        return seatMapper.update(seat);
    }

    @Override
    public boolean deleteSeat(Integer seatId, Integer screeningId) {
        return seatMapper.delete(seatId,screeningId);
    }

    @Override
    public List<Seat> findSeatsByCondition(Seat seat) {
        return seatMapper.selectByIf(seat);
    }
}
