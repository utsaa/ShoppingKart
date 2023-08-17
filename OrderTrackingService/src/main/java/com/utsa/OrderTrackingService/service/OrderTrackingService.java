package com.utsa.OrderTrackingService.service;

import com.utsa.OrderTrackingService.dto.OrderDetails;

public interface OrderTrackingService {
    OrderDetails getOrderDetails(Long id);

    OrderDetails updateLocation(Long itemDetailsId, Long providerId, String location);
}
