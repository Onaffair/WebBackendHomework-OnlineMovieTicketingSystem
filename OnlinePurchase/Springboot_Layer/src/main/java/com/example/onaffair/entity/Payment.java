package com.example.onaffair.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configurable
public class Payment {
    private Integer paymentId;
    private Integer orderId;
    private java.util.Date paymentTime;
    private Double amount;
    private String paymentMethod;
    private String paymentStatus;

}

