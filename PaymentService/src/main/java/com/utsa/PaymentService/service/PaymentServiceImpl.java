package com.utsa.PaymentService.service;

import com.utsa.PaymentService.dto.Bill;
import com.utsa.PaymentService.dto.Item;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymenService{
    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Override
    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallback")
    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallback")
    public Bill doPayment(Item item) {
        logger.info("Item in pay {}", item);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int rand= new Random().nextInt(0,10);
//        int rand= 2;
        logger.info("rand number {}", rand);
        if ((rand%2)==0){
            Bill bill = new Bill();
            bill.setId(UUID.randomUUID().toString());
            bill.setItem(item.getId());
            bill.setPrice(item.getPrice());
            logger.info("generated bill {}", bill);
            return bill;
        }
        return null;
    }


    public Bill circuitBreakerFallback(Item item, Throwable t){
        logger.info("In circuitbreaker {}", t.toString());
        return new Bill();
    }

    public Bill rateLimiterFallback(Item item, Throwable t){
        logger.info("In rate limiter fallback {}", t.toString());
        return new Bill();
    }
}
