package com.example.ClientPortal.controller;

import com.example.ClientPortal.dto.OrderDetails;
import com.example.ClientPortal.service.OrderTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderTrackingController {

    @Autowired
    public OrderTrackingService orderTrackingService;
    @GetMapping("/{id}")
    public OrderDetails getOrderDetails(@PathVariable("id") Long id){
        return orderTrackingService.getOrderDetails(id);
    }

    @GetMapping("/{itemDetailsId}/{providerId}/{location}")
    public OrderDetails updateOrderDetails(@PathVariable("itemDetailsId") Long itemDetailsId, @PathVariable("providerId") Long providerId,
                                           @PathVariable("location") String location){
        return orderTrackingService.updateLocation(itemDetailsId, providerId, location);
    }
}
