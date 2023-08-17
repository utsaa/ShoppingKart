package com.example.ClientPortal.service;

import com.example.ClientPortal.dto.ItemDetail;
import com.example.ClientPortal.service.resilience4j.PaymentServiceResilience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
public PaymentServiceResilience paymentServiceResilience;
    @Override
    public ItemDetail buyNow(Long itemId, Long customerId) {
        return paymentServiceResilience.buyNow(itemId, customerId);
    }
}
