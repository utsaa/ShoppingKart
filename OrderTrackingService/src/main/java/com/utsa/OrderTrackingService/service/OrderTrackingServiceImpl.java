package com.utsa.OrderTrackingService.service;

import com.utsa.OrderTrackingService.dto.Item;
import com.utsa.OrderTrackingService.dto.ItemDetail;
import com.utsa.OrderTrackingService.dto.OrderDetails;
import com.utsa.OrderTrackingService.dto.Provider;
import com.utsa.OrderTrackingService.repository.ItemDetailsRepository;
import com.utsa.OrderTrackingService.repository.ProviderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTrackingServiceImpl implements OrderTrackingService {
@Autowired
public ItemDetailsRepository itemDetailsRepository;

@Autowired
public ProviderRepository providerRepository;
Logger logger= LoggerFactory.getLogger(OrderTrackingServiceImpl.class);
@CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
@RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
@Cacheable(cacheNames = "OrderDetails", key = "#id")
    @Override
    public OrderDetails getOrderDetails(Long id) {
        ItemDetail itemDetail= itemDetailsRepository.findById(id).orElse(null);
logger.info("Item Detail is {}", itemDetail);
        if (itemDetail == null) throw new RuntimeException("No itemDetails by the item Id");
        return itemDetail.getOrderDetails();

    }
    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback1")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback1")
    @CachePut(cacheNames = "OrderDetails", key = "#itemDetailsId")
    @Override
    public OrderDetails updateLocation(Long itemDetailsId, Long providerId, String location) {
       ItemDetail itemDetail= itemDetailsRepository.findById(itemDetailsId).orElse(null);
        if (itemDetail == null) throw new RuntimeException("No itemDetails by the item Id");
        Item item= itemDetail.getItem();
        logger.info("Item Detail is {} item is {}", itemDetail, item);
        Provider provider=providerRepository.findById(providerId).orElse(null);
        logger.info("provider is {}", provider);
        if (provider==null || ! provider.getProvidedItems().contains(item)) throw new RuntimeException("Provider id item mismatch");
        itemDetail.getOrderDetails().setCurrentLocation(location);
        itemDetailsRepository.save(itemDetail);
        logger.info("Item detail is saved");
        return itemDetail.getOrderDetails();

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
