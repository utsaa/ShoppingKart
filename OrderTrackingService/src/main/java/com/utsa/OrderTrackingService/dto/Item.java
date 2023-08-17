package com.utsa.OrderTrackingService.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;



    private String name;

    @Override
    public String toString() {
        return "Item{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getId(), item.getId()) && Objects.equals(getName(), item.getName()) && Objects.equals(getPrice(), item.getPrice()) && Objects.equals(getProvider(), item.getProvider());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getProvider());
    }

    private Long price;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "provider_Id", nullable = false)
    private Provider provider;
}
