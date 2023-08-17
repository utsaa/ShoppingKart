package com.utsa.PaymentService.dto;

import java.io.Serializable;
import java.util.Objects;

public class Bill implements Serializable {
    private String Id;
    private Long  itemId;

    private Long price;

    @Override
    public String toString() {
        return "Bill{" +
                "Id='" + Id + '\'' +
                ", item=" + itemId +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill bill)) return false;
        return Objects.equals(getId(), bill.getId()) && Objects.equals(getItem(), bill.getItem()) && Objects.equals(getPrice(), bill.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItem(), getPrice());
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Long getItem() {
        return itemId;
    }

    public void setItem(Long item) {
        this.itemId = item;
    }



}


