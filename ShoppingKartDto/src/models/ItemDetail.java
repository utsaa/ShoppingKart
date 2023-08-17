package models;

import java.io.Serializable;
import java.util.Objects;

public class ItemDetail implements Serializable {
    private Long Id;
    private Item item;
    private Bill bill;
    private OrderDetails orderDetails;

    @Override
    public String toString() {
        return "ItemDetail{" +
                "Id=" + Id +
                ", item=" + item +
                ", bill=" + bill +
                ", orderDetails=" + orderDetails +
                '}';
    }

    public Long getId() {
        return Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDetail that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getItem(), that.getItem()) && Objects.equals(getBill(), that.getBill()) && Objects.equals(getOrderDetails(), that.getOrderDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItem(), getBill(), getOrderDetails());
    }

    public void setId(Long id) {
        Id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
