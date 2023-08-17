package com.utsa.PaymentService.service;

import com.utsa.PaymentService.dto.Bill;
import com.utsa.PaymentService.dto.Item;

public interface PaymenService {
    Bill doPayment(Item item);
}
