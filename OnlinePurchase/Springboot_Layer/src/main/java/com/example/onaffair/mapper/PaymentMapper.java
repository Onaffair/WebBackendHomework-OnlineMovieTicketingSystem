package com.example.onaffair.mapper;

import com.example.onaffair.entity.Payment;
import java.util.List;

public interface PaymentMapper {
    List<Payment> getAll();

    boolean insert(Payment payment);

    boolean update(Payment payment);

    boolean delete(Integer paymentId);

    List<Payment> selectByIf(Payment payment);
}
