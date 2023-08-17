package com.example.ClientPortal.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Provider extends User{

    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providedItems=" + providedItems +
                super.toString()+
                '}';
    }

    private List<Item> providedItems = new ArrayList<>();



    public List<Item> getProvidedItems() {
        return providedItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider providers)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getProvidedItems(), providers.getProvidedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProvidedItems());
    }

    public void setProvidedItems(List<Item> providedItems) {
        this.providedItems = providedItems;
    }
}
