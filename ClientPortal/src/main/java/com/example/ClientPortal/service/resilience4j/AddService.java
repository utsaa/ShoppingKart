package com.example.ClientPortal.service.resilience4j;


import com.example.ClientPortal.dto.Customer;
import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.dto.Provider;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AddService {

    Logger logger = LoggerFactory.getLogger(AddService.class);
    String baseUrl="http://add-service/getItem";
    @Autowired
    RestTemplate restTemplate;

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallBack")
    @RateLimiter(name="service1", fallbackMethod = "rateLimiterFallBack")
    public Item addItem(Item item, Long providerId) {
        Item item1=restTemplate.postForObject(baseUrl+"/add/{id}",item, Item.class, Map.of("id", providerId));
        return item1;
    }
@CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallBack")
@RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallBack")
    public Item deleteItem(Item item, Long providerId) {
    restTemplate.delete(baseUrl+"/delete/{id}",item, Item.class, Map.of("id", providerId));
    return item;
    }

    public Item rateLimiterFallBack(Item item, Long providerId, Throwable t){
        logger.info("Inside rate limiter fallback {}", t.toString());
        return new Item();
    }

    public Item circuitBreakerFallBack(Item item, Long providerId, Throwable t){
        logger.info("Inside circuit breaker fallback {}", t.toString());
        return new Item();
    }
    public Customer addCustomer(Customer customer) {
Customer customer1=restTemplate.postForObject(baseUrl+"/addCustomer",customer, Customer.class);
return customer1;
    }

    public Provider addProvider(Provider provider) {
        Provider provider1=restTemplate.postForObject(baseUrl+"/addProvider",provider, Provider.class);
        return provider;
    }

    public List<Customer> getAllCustomers() {

        return restTemplate.getForObject(baseUrl+"/getCustomers", List.class);
    }

    public List<Provider> getAllProviders() {
        return restTemplate.getForObject(baseUrl+"/getProviders",List.class);
    }

    public List<Item> getAllItems() {
        return restTemplate.getForObject(baseUrl+"/getItems", List.class);
    }
}
