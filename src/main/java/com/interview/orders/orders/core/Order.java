package com.interview.orders.orders.core;

import java.util.List;

public class Order {
    List<Item> items;
    int orderNumber;

    public Order(int orderNumber, List<Item> items) {
        this.orderNumber = orderNumber;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
