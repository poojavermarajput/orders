package com.interview.orders.orders.data;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {

    public static AtomicInteger orderId = new AtomicInteger(0);

    Map<Integer, Order> ordersList = new HashMap<>();

    public Order addOrder(List<Item> itemList){
        orderId.incrementAndGet();
        Order order = new Order(orderId.get(), itemList );
        ordersList.put(orderId.get(), order);
        return order;
    }

}
