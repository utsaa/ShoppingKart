package com.gl.BuyingAndPaymentService.service;

import com.gl.BuyingAndPaymentService.dto.ItemDetail;

public interface PaymentService {
    ItemDetail buyNow(Long itemId, Long customerId);
}
