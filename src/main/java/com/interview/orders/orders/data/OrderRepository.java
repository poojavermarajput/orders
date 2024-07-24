package com.interview.orders.orders.data;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.Order;
import com.interview.orders.orders.core.OrderSummary;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {

    public static AtomicInteger orderId = new AtomicInteger(0);

    Map<Integer, OrderSummary> ordersList = new HashMap<>();

   /* public OrderSummary addOrder(List<Item> itemList){
        orderId.incrementAndGet();
        Order order = new Order(orderId.get(), itemList );
        ordersList.put(orderId.get(), order);
        return order;
    }*/

    public OrderSummary addOrder(OrderSummary orderSummary){
        orderId.incrementAndGet();
        orderSummary.setOrderNumber(orderId.get());
        //Order order = new Order(orderId.get(), itemList );
        ordersList.put(orderId.get(), orderSummary);
        return orderSummary;
    }

    public OrderSummary getOrder(int orderId) {
        return ordersList.get(orderId);
    }

    public Collection<OrderSummary> getOrders() {
        return ordersList.values();
    }
}
