package com.example.onaffair.service.impl;

import com.example.onaffair.entity.Payment;
import com.example.onaffair.mapper.PaymentMapper;
import com.example.onaffair.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> getAllPayments() {
        return paymentMapper.getAll();
    }

    @Override
    public boolean addPayment(Payment payment) {
        return paymentMapper.insert(payment);
    }

    @Override
    public boolean updatePayment(Payment payment) {
        return paymentMapper.update(payment);
    }

    @Override
    public boolean deletePayment(Integer paymentId) {
        return paymentMapper.delete(paymentId);
    }

    @Override
    public List<Payment> findPaymentsByCondition(Payment payment) {
        return paymentMapper.selectByIf(payment);
    }
}
