package com.example.onaffair.service;

import com.example.onaffair.entity.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();

    boolean addPayment(Payment payment);

    boolean updatePayment(Payment payment);

    boolean deletePayment(Integer paymentId);

    List<Payment> findPaymentsByCondition(Payment payment);
}
