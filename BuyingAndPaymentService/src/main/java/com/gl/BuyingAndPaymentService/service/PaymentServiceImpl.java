package com.gl.BuyingAndPaymentService.service;

import com.gl.BuyingAndPaymentService.dto.ItemDetail;
import com.gl.BuyingAndPaymentService.service.resilience.PaymentServiceResilience;
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
