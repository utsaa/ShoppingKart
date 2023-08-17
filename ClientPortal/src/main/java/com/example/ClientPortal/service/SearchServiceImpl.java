package com.example.ClientPortal.service;


import com.example.ClientPortal.dto.Item;
import com.example.ClientPortal.service.resilience4j.SearchServiceResilience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
 Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    SearchServiceResilience searchServiceResilience;

    @Cacheable(cacheNames = "item", key = "#name")
    @Override
    public List<Item> getItemByName(String name) {
        return searchServiceResilience.getItemByName(name);
    }

    @Cacheable(cacheNames = "item", key = "#name")
    @Override
    public List<Item> getItemByProvider(String name) {
        logger.info("In upper getItemByProvider {}", name);
        return searchServiceResilience.getItemByProvider(name);
    }

    @Cacheable(cacheNames = "items", key = "#name.concat('-').concat(#param)")
    @Override
    public List<Item> getItemByNameSortPrice(String name, String param) {
        return searchServiceResilience.getItemByNameSortPrice(name, param);
    }
}
