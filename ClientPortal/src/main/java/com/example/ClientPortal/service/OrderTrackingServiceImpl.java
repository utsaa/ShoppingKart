package com.example.ClientPortal.service;

import com.example.ClientPortal.dto.OrderDetails;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrderTrackingServiceImpl implements OrderTrackingService {

Logger logger= LoggerFactory.getLogger(OrderTrackingServiceImpl.class);

@Autowired
RestTemplate restTemplate;

String baseUrl="http://order-tracking-service/orders";
@CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
@RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
@Cacheable(cacheNames = "OrderDetails", key = "#id")
    @Override
    public OrderDetails getOrderDetails(Long id) {
        return restTemplate.getForObject(baseUrl+"/{id}", OrderDetails.class, Map.of("id",id));

    }
    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback1")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback1")
    @CachePut(cacheNames = "OrderDetails", key = "#itemDetailsId")
    @Override
    public OrderDetails updateLocation(Long itemDetailsId, Long providerId, String location) {

        return restTemplate.getForObject(baseUrl+"/{itemDetailsId}/{providerId}/{location}", OrderDetails.class,
                Map.of("itemDetailsId", itemDetailsId, "providerId",providerId,"location",location ));

    }

    public OrderDetails circuitBreakerFallback1(Long itemDetailsId, Long providerId, String location, Throwable t){
    logger.info("In circuit breaker fallback {}", t.toString());
    return new OrderDetails();
    }

    public OrderDetails rateLimiterFallback1(Long itemDetailsId, Long providerId, String location, Throwable t){
    logger.info("Inside rate limiter fallback {}", t.toString());
    return new OrderDetails();
    }
public OrderDetails circuitBreakerFallback(Long id, Throwable t){
    logger.info("In circuit breaker fallback {}", t.toString());
    return new OrderDetails();
    }

    public OrderDetails rateLimiterFallback(Long id, Throwable t){
    logger.info("Inside rate limiter fallback {}", t.toString());
    return new OrderDetails();
    }
}
