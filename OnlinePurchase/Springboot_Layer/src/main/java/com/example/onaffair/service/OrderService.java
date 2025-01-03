package com.example.onaffair.service;


import com.example.onaffair.dto.OrderResponseDto;
import com.example.onaffair.entity.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    boolean addOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(Integer orderId);

    List<Order> findOrdersByCondition(Order order);

    List<OrderResponseDto> getOrderList(List<Integer> idList);


}
