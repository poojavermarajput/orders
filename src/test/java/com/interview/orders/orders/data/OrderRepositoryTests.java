package com.interview.orders.orders.data;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderRepositoryTests {

    OrderRepository orderRepository;

    @Before
    public void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    public void itTestsAddingOrders(){
        List<Item> items = new ArrayList<>();
        Item item = new Item("apple", 10);
        items.add(item);
        Order order1 = orderRepository.addOrder(items);

        Assert.assertEquals(order1.getOrderNumber(), 1 );
        Assert.assertEquals(order1.getItems(), items );

        Item item2 = new Item("orange", 20);
        items.add(item2);
        Order order2 = orderRepository.addOrder(items);
        Assert.assertEquals(order2.getOrderNumber(), 2 );
        Assert.assertEquals(order2.getItems(), items );

    }
}
