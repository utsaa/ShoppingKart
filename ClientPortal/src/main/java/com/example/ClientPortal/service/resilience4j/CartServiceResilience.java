package com.example.ClientPortal.service.resilience4j;


import com.example.ClientPortal.dto.Customer;
import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.dto.ItemDetail;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceResilience {

    Logger logger= LoggerFactory.getLogger(CartServiceResilience.class);

    String baseUrl="http://buying-and-payment-service/buyAndPay";
    @Autowired
    RestTemplate restTemplate;
    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
    public Item addItemToCart(Long itemId, Long customerId) {

        return restTemplate.getForObject(baseUrl+"/addToCart/{itemId}/{id}", Item.class,
                Map.of("itemId",itemId,"id",customerId));
    }

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
    public Item removeItemFromCart(Long itemId, Long customerId) {
        return restTemplate.getForObject(baseUrl+"/removeItem/{itemId}/{id}", Item.class,
                Map.of("itemId",itemId,"id",customerId));
    }

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback1")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback1")
    public List<Item> viewCartItems(Long customerId) {
        return restTemplate.getForObject(baseUrl+"/viewCartItems/{id}",List.class,Map.of("id", customerId));
    }

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback2")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback2")
    public List<ItemDetail> getOrderDetails(Long customerId) {
        return restTemplate.getForObject(baseUrl+"/getOrders/{id}",List.class,Map.of("id", customerId));
    }


    public Item circuitBreakerFallback(Long itemId, Long customerId, Throwable t){
        logger.info("Inside circuit breaker {}", t.toString());
        return new Item();
    }
    public Item rateLimiterFallback(Long itemId, Long customerId, Throwable t){
        logger.info("Inside rate limiter fallback {}", t.toString());
        return new Item();
    }

    public List<Item> circuitBreakerFallback1(Long customerId, Throwable t){
        logger.info("Inside circuit breaker 1 {}", t.toString());
        return new ArrayList<>();
    }
    public List<Item> rateLimiterFallback1(Long customerId, Throwable t){
        logger.info("Inside rate limiter fallback 1 {}", t.toString());
        return new ArrayList<>();
    }
    public List<ItemDetail> circuitBreakerFallback2(Long customerId, Throwable t){
        logger.info("Inside circuit breaker 2 {}", t.toString());
        return new ArrayList<>();
    }
    public List<ItemDetail> rateLimiterFallback2(Long customerId, Throwable t){
        logger.info("Inside rate limiter fallback 2 {}", t.toString());
        return new ArrayList<>();
    }

}
