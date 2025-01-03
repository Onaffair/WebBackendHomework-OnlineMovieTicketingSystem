package com.example.onaffair.mapper;

import com.example.onaffair.dto.OrderResponseDto;
import com.example.onaffair.entity.Order;
import java.util.List;

public interface OrderMapper {
    List<Order> getAll();

    boolean insert(Order order);

    boolean update(Order order);

    boolean delete(Integer orderId);

    List<Order> selectByIf(Order order);

    List<OrderResponseDto> getOrderList(List<Integer> idList);

}
