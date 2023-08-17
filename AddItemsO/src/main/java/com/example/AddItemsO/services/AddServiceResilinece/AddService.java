package com.example.AddItemsO.services.AddServiceResilinece;

import com.example.AddItemsO.Repository.CustomerRepository;
import com.example.AddItemsO.Repository.ItemRepository;
import com.example.AddItemsO.Repository.ProviderRepository;
import com.example.AddItemsO.dto.Customer;
import com.example.AddItemsO.dto.Item;
import com.example.AddItemsO.dto.Provider;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddService {

    Logger logger = LoggerFactory.getLogger(AddService.class);
    @Autowired
public ItemRepository itemRepository;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public ProviderRepository providerRepository;

    @CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallBack")
    @RateLimiter(name="service1", fallbackMethod = "rateLimiterFallBack")
    public Item addItem(Item item, Long providerId) {
        Optional<Provider> provider=providerRepository.findById(providerId);
        if (provider.isEmpty()) throw new RuntimeException("No such provider found");
        Provider providerValue=provider.get();
        List<Item> items = providerValue.getProvidedItems();
        item.setProvider(providerValue);
        logger.info("The provider found is {} item {}", providerValue, item);
        itemRepository.save(item);
        logger.info("item saved");
        items.add(item);
        providerValue.setProvidedItems(items);
//        providerRepository.updateProvidedItems(providerId,  items);
        providerRepository.save(providerValue);
        logger.info("provider saved");
        return item;
    }
@CircuitBreaker(name = "service1", fallbackMethod = "circuitBreakerFallBack")
@RateLimiter(name = "service1", fallbackMethod = "rateLimiterFallBack")
    public Item deleteItem(Item item, Long providerId) {
    if (!item.getProvider().getId().equals(providerId)){
        throw new RuntimeException("Item id mismatch");
    }
    Optional<Provider> provider=providerRepository.findById(providerId);
    if (provider.isEmpty()) throw new RuntimeException("No such provider found");
    Provider providerValue=provider.get();
    List<Item> items = providerValue.getProvidedItems();
        itemRepository.delete(item);
        items.remove(item);
        providerRepository.updateProvidedItems(providerId, items);
    logger.info("Item deleted");
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
customerRepository.save(customer);
return customer;
    }

    public Provider addProvider(Provider provider) {
        providerRepository.save(provider);
        return provider;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
