package com.interview.orders.orders.core;

import java.util.Objects;

public class Inventory {
    private String name;
    private double price;
    private int id;

    public Inventory() {
        this.name = name;
        this.price = price;
    }

    public Inventory(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Double.compare(getPrice(), inventory.getPrice()) == 0 && getId() == inventory.getId() && Objects.equals(getName(), inventory.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getId());
    }
}
