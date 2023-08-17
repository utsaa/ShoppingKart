package com.example.AddItemsO.services;

import com.example.AddItemsO.dto.Customer;
import com.example.AddItemsO.dto.Item;
import com.example.AddItemsO.dto.Provider;
import com.example.AddItemsO.services.AddServiceResilinece.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    public AddService addService;
    @Override
    public Item addItem(Item item, Long providerId) {
        return addService.addItem(item, providerId);
    }

    @Override
    public Item deleteItem(Item item, Long providerId) {
        return addService.deleteItem(item, providerId);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return addService.addCustomer(customer);
    }

    @Override
    public Provider addProvider(Provider provider) {
        return addService.addProvider(provider);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return addService.getAllCustomers();
    }

    @Override
    public List<Provider> getAllProviders() {
        return addService.getAllProviders();
    }

    @Override
    public List<Item> getAllItems() {
        return addService.getAllItems();
    }
}
