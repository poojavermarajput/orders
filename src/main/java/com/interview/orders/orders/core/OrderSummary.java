package com.interview.orders.orders.core;

import java.util.List;
import java.util.Objects;

public class OrderSummary {
    List<ItemWithCost> itemCosts;
    int orderNumber;
    double totalCost;

    public OrderSummary(double totalCost, List<ItemWithCost> itemCosts) {
        this.itemCosts = itemCosts;
        this.totalCost = totalCost;
    }

    public OrderSummary(int orderNumber, double totalCost, List<ItemWithCost> itemCosts) {
        this.orderNumber = orderNumber;
        this.itemCosts = itemCosts;
        this.totalCost = totalCost;
    }

    public static OrderSummary generateOrderSummary(List<ItemWithCost> itemCosts) {
        double totalCost = 0;
        for (ItemWithCost itemCost : itemCosts) {
            totalCost+=itemCost.getCost();
        }
       return new OrderSummary(totalCost, itemCosts);
    }

    public List<ItemWithCost> getItemCosts() {
        return itemCosts;
    }

    public void setItemCosts(List<ItemWithCost> itemCosts) {
        this.itemCosts = itemCosts;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSummary that = (OrderSummary) o;
        return getOrderNumber() == that.getOrderNumber() && Double.compare(getTotalCost(), that.getTotalCost()) == 0 && Objects.equals(getItemCosts(), that.getItemCosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemCosts(), getOrderNumber(), getTotalCost());
    }
}
