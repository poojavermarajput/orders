package com.interview.orders.orders.core;

import java.util.Objects;

public class ItemWithCost extends ItemWithPrice{
    double cost;

    public ItemWithCost(String name, int quantity, double price, double cost) {
        super(name, quantity, price);
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemWithCost itemCost = (ItemWithCost) o;
        return Double.compare(getCost(), itemCost.getCost()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCost());
    }

    /*public OrderCost(int orderNumber, List<ItemWithPrice> itemWithPrices) {
        this.orderNumber = orderNumber;
        this.itemWithPrices = itemWithPrices;
    }

    public static OrderCost getOrderWithCost(Order order, Map<String, Double> itemPrices) {
        List<ItemWithPrice> itemWithPricesTemp = order.getItems().stream().map(item -> new ItemWithPrice((item.getName()), item.getQuantity(), itemPrices.get(item.getName()))).toList();
        return new OrderCost(order.getOrderNumber(), itemWithPricesTemp);
    }

    public List<ItemWithPrice> getItemWithPrices() {
        return itemWithPrices;
    }

    public void setItemWithPrices(List<ItemWithPrice> itemWithPrices) {
        this.itemWithPrices = itemWithPrices;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }*/
}
