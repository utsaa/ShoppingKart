package com.example.ClientPortal.service;


import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.dto.ItemDetail;
import com.example.ClientPortal.service.resilience4j.CartServiceResilience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    public CartServiceResilience cartServiceResilience;

    @CachePut(cacheNames = "cartItems", key = "#customerId")
    @Override
    public Item addItemToCart(Long itemId, Long customerId) {
        return cartServiceResilience.addItemToCart(itemId, customerId);
    }

    @CachePut(cacheNames = "cartItems", key = "#customerId")
    @Override
    public Item removeItemFromCart(Long itemId, Long customerId) {
        return cartServiceResilience.removeItemFromCart(itemId, customerId);
    }

    @Cacheable(cacheNames = "cartItems", key = "#customerId")
    @Override
    public List<Item> viewCartItems(Long customerId) {
        return cartServiceResilience.viewCartItems(customerId);
    }

    @Cacheable(cacheNames = "orderItems", key = "#customerId")
    @Override
    public List<ItemDetail> getOrderDetails(Long customerId) {
        return cartServiceResilience.getOrderDetails(customerId);
    }
}
