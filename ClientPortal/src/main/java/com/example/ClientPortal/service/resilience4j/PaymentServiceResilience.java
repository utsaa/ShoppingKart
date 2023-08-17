package com.example.ClientPortal.service.resilience4j;

import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.dto.ItemDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PaymentServiceResilience {
    Logger logger= LoggerFactory.getLogger(PaymentServiceResilience.class);

    @Autowired
    public RestTemplate restTemplate;
    String baseUrl="http://buying-and-payment-service/buyAndPay";


    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback2")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback2")
    public ItemDetail buyNow(Long itemId, Long customerId) {
        return restTemplate.getForObject(baseUrl+"/buy/{ItemId}/{id}", ItemDetail.class,Map.of("ItemId",itemId,"id", customerId));
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
