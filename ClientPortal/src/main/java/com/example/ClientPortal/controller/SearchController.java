package com.example.ClientPortal.controller;

import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getItem")
public class SearchController {

    @Autowired
    private SearchService service;

    @GetMapping("/ItemByName/{name}")
    public List<Item> getItemByName(@PathVariable String name){
        return service.getItemByName(name);
    }

    @GetMapping("/ItemByProvider/{name}")
    public List<Item> getItemByProvider(@PathVariable String name){
        return service.getItemByProvider(name);
    }

    @GetMapping("/{name}/{param}")
    public List<Item> getItemByNameSortPrice(@PathVariable String name, @PathVariable String param){
        return service.getItemByNameSortPrice(name, param);
    }

}
