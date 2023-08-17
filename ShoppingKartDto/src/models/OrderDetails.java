package models;

import java.util.Objects;

public class OrderDetails {
    private String currentLocation;
    private Item item;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "currentLocation='" + currentLocation + '\'' +
                ", item=" + item +
                ", orderDelivered=" + orderDelivered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails that)) return false;
        return isOrderDelivered() == that.isOrderDelivered() && Objects.equals(getCurrentLocation(), that.getCurrentLocation()) && Objects.equals(getItem(), that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentLocation(), getItem(), isOrderDelivered());
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(boolean orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    private boolean orderDelivered;
}
