package com.gl.BuyingAndPaymentService.dto;

import io.micrometer.core.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "itemDetail")
public class ItemDetail implements Serializable {
    public ItemDetail(){

    };
    @Id
    private Long Id;

    public ItemDetail(Long id){
        this.Id=id;
    }

    public Long getId() {
        return Id;
    }


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item ;



    @Embedded

    private Bill bill = new Bill();
    @Embedded
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