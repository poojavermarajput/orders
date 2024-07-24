package com.interview.orders.orders.data;

import com.interview.orders.orders.core.ItemWithCost;
import com.interview.orders.orders.core.OrderSummary;
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
        List<ItemWithCost> items = new ArrayList<>();
        ItemWithCost item = new ItemWithCost("apple", 10, 2, 10);
        items.add(item);
        OrderSummary orderSummary = new OrderSummary(0, new ArrayList<>());
        OrderSummary orderSummaryActual1 = orderRepository.addOrder(orderSummary);

        Assert.assertEquals(orderSummaryActual1.getOrderNumber(), 1 );
        Assert.assertEquals(orderSummaryActual1.getItemCosts(), new ArrayList<>() );

        ItemWithCost item2 = new ItemWithCost("orange", 10, 2, 10);
        items.add(item2);
        OrderSummary orderSummary2 = new OrderSummary(0, items);
        OrderSummary orderSummaryActual = orderRepository.addOrder(orderSummary2);
        Assert.assertEquals(orderSummaryActual.getOrderNumber(), 2 );
        Assert.assertEquals(orderSummaryActual.getItemCosts(), items );

    }
}
