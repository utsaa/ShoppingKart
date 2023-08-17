package com.example.ClientPortal.service.resilience4j;

import com.example.ClientPortal.dto.Item;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceResilience {
    Logger logger = LoggerFactory.getLogger(SearchServiceResilience.class);
String baseUrl="http://search-service/getItem";

@Autowired
RestTemplate restTemplate;
    @CircuitBreaker(name="service1", fallbackMethod = "fallBack")
    @RateLimiter(name="service1", fallbackMethod = "rateLimiterFallback")
    @Retry(name = "retryService1", fallbackMethod = "retryfallback")
    @Bulkhead(name = "bulkheadService1", fallbackMethod = "bulkHeadFallback")
    public List<Item> getItemByName(String name) {
logger.info("In getItemByName {}", name);
        return restTemplate.getForObject(baseUrl+"/ItemByName/{name}",List.class, Map.of("name", name));
    }

    @CircuitBreaker(name="service1", fallbackMethod = "fallBack")
    @RateLimiter(name="service1", fallbackMethod = "rateLimiterFallback")
    @Retry(name = "retryService1", fallbackMethod = "retryfallback")
    @Bulkhead(name = "bulkheadService1", fallbackMethod = "bulkHeadFallback")
    public List<Item> getItemByProvider(String name) {
        logger.info("In getItemByProvider {}", name);
        return restTemplate.getForObject(baseUrl+"/ItemByProvider/{name}",List.class,Map.of("name", name));
    }

    @CircuitBreaker(name="service1", fallbackMethod = "fallBackSortParam")
//    @RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallBackSortParam")
    public List<Item> getItemByNameSortPrice(String name, String param) {
        return restTemplate.getForObject(baseUrl+"/{name}/{param}",List.class,Map.of("name", name, "param", param));
    }

    public List<Item> fallBack(String name, Throwable t){
        logger.info("Has gone to fall back method {}", t.getCause().toString());
        return List.of(new Item());
    }

    public List<Item> rateLimiterFallback(String name, Throwable t) {
        logger.error("Inside rateLimiterFallback, cause - {}", t.toString());
        return List.of(new Item());
    }
    public List<Item> bulkHeadFallback(String name, Throwable t) {
        logger.error("Inside bulkHeadFallback, cause - {}", t.toString());
        return List.of(new Item());
    }
    public List<Item> retryfallback(String name, Throwable t) {
        logger.error("Inside retryfallback, cause - {}", t.toString());
        return List.of(new Item());
    }

    public List<Item> fallBackSortParam(String name, String param, Throwable t){
        logger.info( "CircuitBreaker fall back for sort param {}",t.toString());
        return new ArrayList<>();
    }

    public List<Item> rateLimiterFallBackSortParam(String name, String param, Throwable t){
        logger.info("Rate limiter fall back of sort param {}", t.toString());
        return new ArrayList<>();
    }
}
