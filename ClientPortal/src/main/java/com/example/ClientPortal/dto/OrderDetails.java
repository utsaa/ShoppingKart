package com.example.ClientPortal.dto;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetails implements Serializable {
    private String currentLocation;
    private Long orderDetailsItemId;

    public boolean isOrderConfirmed() {
        return orderConfirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails that)) return false;
        return isOrderConfirmed() == that.isOrderConfirmed() && isOrderDelivered() == that.isOrderDelivered() && Objects.equals(getCurrentLocation(), that.getCurrentLocation()) && Objects.equals(getItem(), that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentLocation(), getItem(), isOrderConfirmed(), isOrderDelivered());
    }

    public void setOrderConfirmed(boolean orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    private boolean orderConfirmed;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "currentLocation='" + currentLocation + '\'' +
                ", item=" + orderDetailsItemId +
                ", orderConfirmed=" + orderConfirmed +
                ", orderDelivered=" + orderDelivered +
                '}';
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Long getItem() {
        return orderDetailsItemId;
    }

    public void setItem(Long item) {
        this.orderDetailsItemId = item;
    }

    public boolean isOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(boolean orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    private boolean orderDelivered;
}
