package com.example.ClientPortal.service;

import com.example.ClientPortal.dto.Item;

import java.util.List;

public interface SearchService {
    List<Item> getItemByName(String name);

    List<Item> getItemByProvider(String name);

    List<Item> getItemByNameSortPrice(String name, String param);
}
