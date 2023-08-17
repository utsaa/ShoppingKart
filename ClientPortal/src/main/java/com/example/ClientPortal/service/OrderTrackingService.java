package com.example.ClientPortal.service;

import com.example.ClientPortal.dto.OrderDetails;

public interface OrderTrackingService {
    OrderDetails getOrderDetails(Long id);

    OrderDetails updateLocation(Long itemDetailsId, Long providerId, String location);
}
