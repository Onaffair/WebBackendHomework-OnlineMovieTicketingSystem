package com.example.onaffair.controller;

import com.example.onaffair.entity.Seat;
import com.example.onaffair.service.SeatService;
import com.example.onaffair.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/getSeatByScreening")
    public Result<List<Integer>> getSeatByScreening(@Param("screeningId") Integer screeningId) {
        List<Integer> res = seatService.findSeatsByCondition(new Seat(){{
            setScreeningId(screeningId);
        }}).stream().map(Seat::getSeatId).toList();
        return new Result<>(Result.success().getCode(), "请求成功", res);
    }

}
