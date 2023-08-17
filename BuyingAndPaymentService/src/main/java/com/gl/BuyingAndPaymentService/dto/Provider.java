package com.gl.BuyingAndPaymentService.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "provider")
public class Provider extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.EAGER,targetEntity = Item.class, mappedBy = "provider")
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
