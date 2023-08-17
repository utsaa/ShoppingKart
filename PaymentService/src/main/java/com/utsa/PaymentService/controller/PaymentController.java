package com.utsa.PaymentService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utsa.PaymentService.dto.Bill;
import com.utsa.PaymentService.dto.Item;
import com.utsa.PaymentService.service.PaymenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    Logger logger= LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    public PaymenService paymenService;
@PostMapping
    public Bill doPayment(@RequestBody Item item) {
    logger.info("Item is {}", item);
    return paymenService.doPayment(item);
}
@GetMapping("/{str}")
    public Bill doPayment1(@PathVariable("str") String str){
    Item item= null;
    try {
        item = new ObjectMapper().readValue(str, Item.class);
    } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
    }
    logger.info("Item is {}", item);
    return paymenService.doPayment(item);

    }
}
