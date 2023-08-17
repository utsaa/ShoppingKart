package models;

import java.util.List;
import java.util.Objects;

public class Customer extends User{


    public List<Item> getCartItems() {
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

    public void setCartItems(List<Item> cartItems) {
        this.cartItems = cartItems;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    private List<Item> cartItems;
    private List<ItemDetail> itemDetails;
}
