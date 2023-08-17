package com.gl.BuyingAndPaymentService.controller;

import com.gl.BuyingAndPaymentService.dto.Customer;
import com.gl.BuyingAndPaymentService.dto.Item;
import com.gl.BuyingAndPaymentService.dto.ItemDetail;
import com.gl.BuyingAndPaymentService.service.CartService;
import com.gl.BuyingAndPaymentService.service.PaymentService;
//import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyAndPay")
public class BuyingAndPaymentController {

    @Autowired
    public CartService cartService;

    @Autowired
    public PaymentService paymentService;


    @GetMapping("/addToCart/{itemId}/{id}")
    public Item addItemToCart(@PathVariable("itemId")Long itemId,@PathVariable("id") Long customerId){
        return cartService.addItemToCart(itemId, customerId);
    }

    @GetMapping("/removeItem/{itemId}/{id}")
    public Item removeItemFromCart(@PathVariable("itemId") Long  itemId, @PathVariable("id") Long customerId){
        return cartService.removeItemFromCart(itemId, customerId);
    }

    @GetMapping("/viewCartItems/{id}")
    public List<Item> viewCartItems(@PathVariable("id") Long customerId){
        return cartService.viewCartItems(customerId);
    }


    @GetMapping("/getOrders/{id}")
    public List<ItemDetail> getOrderDetails(@PathVariable("id") Long customerId){
        return cartService.getOrderDetails(customerId);
    }


    @GetMapping("/buy/{ItemId}/{id}")
    public ItemDetail buyNow(@PathVariable("ItemId") Long itemId, @PathVariable("id") Long customerId){
        return paymentService.buyNow(itemId, customerId);
    }

}
