package com.gl.BuyingAndPaymentService.service.resilience;

import com.gl.BuyingAndPaymentService.dto.Customer;
import com.gl.BuyingAndPaymentService.dto.Item;
import com.gl.BuyingAndPaymentService.dto.ItemDetail;
import com.gl.BuyingAndPaymentService.repositories.CustomerRepository;
import com.gl.BuyingAndPaymentService.repositories.ItemRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceResilience {

    Logger logger= LoggerFactory.getLogger(CartServiceResilience.class);
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public ItemRepository itemRepository;
    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
    public Item addItemToCart(Long itemId, Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        Item item=itemRepository.findById(itemId).orElse(null);
        logger.info("customer {} item {}", customer, item);
        customer.getCartItems().add(item);
        customerRepository.save(customer);
        logger.info("Item added");
        return item;
    }

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
    public Item removeItemFromCart(Long itemId, Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        Item item=itemRepository.findById(itemId).orElse(null);
        customer.getCartItems().remove(item);

        customerRepository.save(customer);
        return item;
    }

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback1")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback1")
    public List<Item> viewCartItems(Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        return List.copyOf(customer.getCartItems());
    }

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback2")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback2")
    public List<ItemDetail> getOrderDetails(Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        logger.info("Customer in get orders {}", customer);
        return List.copyOf(customer.getItemDetails());
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
