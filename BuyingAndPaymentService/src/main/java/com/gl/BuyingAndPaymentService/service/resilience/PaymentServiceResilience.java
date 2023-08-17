package com.gl.BuyingAndPaymentService.service.resilience;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gl.BuyingAndPaymentService.dto.*;
import com.gl.BuyingAndPaymentService.repositories.CustomerRepository;
import com.gl.BuyingAndPaymentService.repositories.ItemDetailRepository;
import com.gl.BuyingAndPaymentService.repositories.ItemRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceResilience {
    Logger logger= LoggerFactory.getLogger(PaymentServiceResilience.class);

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public ItemDetailRepository itemDetailRepository;

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback2")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback2")
    public ItemDetail buyNow(Long itemId, Long customerId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String str;
        Item item;
        item = itemRepository.findById(itemId).orElse(null);
        if (item == null) throw new RuntimeException("Item not found");
        logger.info("Item {}", item);
        try {
            str = objectMapper.writeValueAsString(item);
            logger.info("The parsed string {}", str);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String baseUrl="http://payment-service/pay";
//        String baseUrl="";
        Bill bill= restTemplate.getForObject(baseUrl+"/{str}", Bill.class, Map.of("str", str));
        if (bill==null) throw new RuntimeException("Check the random value");
        Customer customer=customerRepository.findById(customerId).orElse(null);
        logger.info("customer {}", customer);
        if (customer==null) throw new RuntimeException("customer not found");
        ItemDetail oldItemDetail=null;
        try {
            oldItemDetail = itemDetailRepository.findById(item.getId()).orElse(null);
        }catch (Exception e){

        }
        if (oldItemDetail != null) {
            customer.getItemDetails().remove(oldItemDetail);
            logger.info("removed item from customer");
            itemDetailRepository.delete(oldItemDetail);
            logger.info("old itemDetails {}", oldItemDetail);
        }
//        Bill bill=restTemplate.postForObject("/",item,Bill.class);
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setOrderDelivered(false);
        orderDetails.setItem(item.getId());
        orderDetails.setOrderConfirmed(bill == null);
        ItemDetail itemDetail=new ItemDetail(item.getId());
        itemDetail.setBill(bill);
        itemDetail.setItem(item);
        itemDetail.setOrderDetails(orderDetails);
        logger.info("New Item Detail {}", itemDetail);
        itemDetailRepository.save(itemDetail);
        logger.info("Item details saved");
        customer.getItemDetails().add(itemDetail);
        logger.info("New customer going to save {}", customer);
        customerRepository.save(customer);
        logger.info("Customer saved");
        return itemDetail;
    }


    public ItemDetail circuitBreakerFallback2(Long itemId,Long customerId, Throwable t){
        logger.info("Inside circuit breaker 2 {}", t.toString());
        return new ItemDetail(null);
    }
    public ItemDetail rateLimiterFallback2(Long itemId,Long customerId, Throwable t) {
        logger.info("Inside rate limiter fallback 2 {}", t.toString());
        return new ItemDetail(null);

    }
}
