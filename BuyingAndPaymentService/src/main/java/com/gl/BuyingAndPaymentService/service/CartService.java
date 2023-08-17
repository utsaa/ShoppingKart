package com.gl.BuyingAndPaymentService.service;

import com.gl.BuyingAndPaymentService.dto.Customer;
import com.gl.BuyingAndPaymentService.dto.Item;
import com.gl.BuyingAndPaymentService.dto.ItemDetail;

import java.util.List;

public interface CartService {
    Item addItemToCart(Long itemId, Long customerId);

    Item removeItemFromCart(Long itemId, Long customerId);

    List<Item> viewCartItems(Long customerId);

    List<ItemDetail> getOrderDetails(Long customerId);
}
