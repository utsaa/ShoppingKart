package com.example.AddItemsO.services;

import com.example.AddItemsO.dto.Customer;
import com.example.AddItemsO.dto.Item;
import com.example.AddItemsO.dto.Provider;

import java.util.List;

public interface ItemService {
    Item addItem(Item item, Long providerId);

    Item deleteItem(Item item, Long providerId);

    Customer addCustomer(Customer customer);

    Provider addProvider(Provider provider);

    List<Customer> getAllCustomers();

    List<Provider> getAllProviders();

    List<Item> getAllItems();
}
