package com.example.ClientPortal.service;


import com.example.ClientPortal.dto.ItemDetail;

public interface PaymentService {
    ItemDetail buyNow(Long itemId, Long customerId);
}
