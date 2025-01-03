package com.example.onaffair.service.impl;

import com.example.onaffair.dto.OrderResponseDto;
import com.example.onaffair.entity.Order;
import com.example.onaffair.mapper.OrderMapper;
import com.example.onaffair.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAll();
    }

    @Override
    public boolean addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.update(order);
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        return orderMapper.delete(orderId);
    }

    @Override
    public List<Order> findOrdersByCondition(Order order) {
        return orderMapper.selectByIf(order);
    }

    @Override
    public List<OrderResponseDto> getOrderList(List<Integer> idList) {
        return orderMapper.getOrderList(idList);
    }
}
