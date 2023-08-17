package com.gl.BuyingAndPaymentService.dto;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Item> getCartItems() {
        return cartItems;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cartItems=" + cartItems +
                ", itemDetails=" + itemDetails +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCartItems(), customer.getCartItems()) && Objects.equals(getItemDetails(), customer.getItemDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCartItems(), getItemDetails());
    }

    public void setCartItems(Set<Item> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(Set<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Item.class)
    private Set<Item> cartItems = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ItemDetail.class)
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ItemDetail> itemDetails = new HashSet<>();
}
