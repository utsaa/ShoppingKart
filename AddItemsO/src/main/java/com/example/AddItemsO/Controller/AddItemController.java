package com.example.AddItemsO.Controller;

import com.example.AddItemsO.dto.Customer;
import com.example.AddItemsO.dto.Item;
import com.example.AddItemsO.dto.Provider;
import com.example.AddItemsO.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ItemControl")
public class AddItemController {
@Autowired
    public ItemService itemService;

@PostMapping("/add/{id}")
    public Item addItem(@RequestBody Item item, @PathVariable("id") Long providerId){
        return itemService.addItem(item, providerId);

    }

    @DeleteMapping("/delete/{id}")
    public Item deleteItem(@RequestBody Item item, @PathVariable("id") Long providerId){
        return itemService.deleteItem(item, providerId);
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){
        return itemService.addCustomer(customer);
    }

    @PostMapping("/addProvider")
    public Provider addProvider(@RequestBody Provider provider){
return itemService.addProvider(provider);
    }


    @GetMapping("/getCustomers")
    public List<Customer> getAllCustomers(){
    return itemService.getAllCustomers();
    }
    @GetMapping("/getProviders")
    public List<Provider> getAllProviders(){
    return itemService.getAllProviders();
    }

    @GetMapping("/getItems")
    public List<Item> getAllItems(){
    return  itemService.getAllItems();
    }
}
