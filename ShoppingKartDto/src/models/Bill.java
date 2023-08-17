package models;

import java.util.Objects;

public class Bill {
    private boolean orderConfirmed;
    private String Id;
    private Item item;

    @Override
    public String toString() {
        return "Bill{" +
                "orderConfirmed=" + orderConfirmed +
                ", Id='" + Id + '\'' +
                ", item=" + item +
                ", orderDelivered=" + orderDelivered +
                '}';
    }

    public boolean isOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(boolean orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill bill)) return false;
        return isOrderConfirmed() == bill.isOrderConfirmed() && isOrderDelivered() == bill.isOrderDelivered() && Objects.equals(getId(), bill.getId()) && Objects.equals(getItem(), bill.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOrderConfirmed(), getId(), getItem(), isOrderDelivered());
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
