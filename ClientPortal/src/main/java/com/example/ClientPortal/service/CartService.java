package com.example.ClientPortal.service;

import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.dto.ItemDetail;


import java.util.List;

public interface CartService {
    Item addItemToCart(Long itemId, Long customerId);

    Item removeItemFromCart(Long itemId, Long customerId);

    List<Item> viewCartItems(Long customerId);

    List<ItemDetail> getOrderDetails(Long customerId);
}
