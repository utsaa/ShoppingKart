package com.example.ClientPortal.dto;

import java.io.Serializable;
import java.util.Objects;


public class ItemDetail implements Serializable {
    public ItemDetail(){

    };

    private Long Id;

    public ItemDetail(Long id){
        this.Id=id;
    }

    public Long getId() {
        return Id;
    }


    private Item item ;


    private Bill bill = new Bill();

    private OrderDetails orderDetails = new OrderDetails();

    @Override
    public String toString() {
        return "ItemDetail{" +
                "id="+Id+
                ", item=" + item +
                ", bill=" + bill +
                ", orderDetails=" + orderDetails +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDetail that)) return false;
        return Objects.equals(getItem(), that.getItem()) && Objects.equals(getBill(), that.getBill()) && Objects.equals(getOrderDetails(), that.getOrderDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getItem(), getBill(), getOrderDetails());
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