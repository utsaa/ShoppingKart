package com.example.ClientPortal.dto;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Customer extends User{


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

    private Set<Item> cartItems = new HashSet<>();

    private Set<ItemDetail> itemDetails = new HashSet<>();
}
