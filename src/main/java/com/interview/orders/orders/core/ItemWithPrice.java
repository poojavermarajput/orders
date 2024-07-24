package com.interview.orders.orders.core;

import java.util.Objects;

public class ItemWithPrice {
    private String name;
    private int quantity;
    private double price;

    public ItemWithPrice(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemWithPrice that = (ItemWithPrice) o;
        return getQuantity() == that.getQuantity() && Double.compare(getPrice(), that.getPrice()) == 0 && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getQuantity(), getPrice());
    }
}
