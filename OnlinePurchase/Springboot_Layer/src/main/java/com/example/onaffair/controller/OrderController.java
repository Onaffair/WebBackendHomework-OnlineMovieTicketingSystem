package com.example.onaffair.controller;

import com.example.onaffair.dto.OrderDto;
import com.example.onaffair.dto.OrderResponseDto;
import com.example.onaffair.entity.Order;
import com.example.onaffair.entity.Seat;
import com.example.onaffair.service.OrderService;
import com.example.onaffair.service.SeatService;
import com.example.onaffair.utils.Result;
import com.example.onaffair.utils.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private SeatService seatService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/changeOrder")
    @ResponseBody
    public Result<Integer> purchase(@RequestBody OrderDto orderDto) {
        if (orderDto.getOrderId() == null){
            seatService.addSeat(new Seat(){{
                setScreeningId(orderDto.getScreeningId());
                setSeatId(orderDto.getSeatId());
            }});
            orderService.addOrder(new Order(){{
                setUserId(orderDto.getUserId());
                setScreeningId(orderDto.getScreeningId());
                setSeatId(orderDto.getSeatId());
            }});
            Integer orderId = orderService.findOrdersByCondition(new Order(){{
                setUserId(orderDto.getUserId());
                setScreeningId(orderDto.getScreeningId());
                setSeatId(orderDto.getSeatId());
            }}).get(0).getOrderId();
            return new Result<>(Result.success().getCode(), "购买成功",orderId);
        }else{

            List<Order> orders = orderService.findOrdersByCondition(new Order() {{
                setOrderId(orderDto.getOrderId());
            }});
            if (orders.isEmpty()){
                return new Result<>(ResultCode.BAD_REQUEST.getCode(),"订单不存在",null);
            }
            Integer orderId = orders.get(0).getOrderId();
            Integer screeningId = orders.get(0).getScreeningId();
            Integer seatId = orders.get(0).getSeatId();
            orderService.deleteOrder(orderId);
            seatService.deleteSeat(seatId,screeningId);
            return new Result<>(Result.success().getCode(), "退票成功",null);
        }

    }

    @GetMapping("/getOrder")
    @ResponseBody
    public Result<List<Integer>> getOrderId(@Param("userId") Integer userId){
        List<Integer> res = orderService.findOrdersByCondition(new Order(){{
            setUserId(userId);
        }}).stream().map(Order::getOrderId).toList();

        return new Result<>(Result.success().getCode(), "请求成功", res);
    }
    @GetMapping("/orderList")
    @ResponseBody
    public Result<List<OrderResponseDto>> getOrderList(@Param("idList") String idList){
        List<Integer> ids = Arrays.stream(idList.split(","))
                .map(Integer::valueOf)
                .toList();

        List<OrderResponseDto> res = orderService.getOrderList(ids);
        return new Result<>(Result.success().getCode(), "请求成功", res);
    }
}
