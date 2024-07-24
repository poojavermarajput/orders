package com.interview.orders.orders.service;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.ItemWithCost;
import com.interview.orders.orders.core.Order;
import com.interview.orders.orders.core.OrderSummary;
import com.interview.orders.orders.data.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTests {
    OrdersService ordersService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    InventoryService inventoryService;

    List<Item> items = new ArrayList<>();
    List<ItemWithCost> itemCosts = new ArrayList<>();
    Map<String, Double> costMap = new HashMap<>();

    @Before
    public void setUp(){
        ordersService = new OrdersService(inventoryService, orderRepository);
        items.addAll(getItems());
        double price =0.65;
        items.forEach(item-> costMap.put(item.getName(), price));

        itemCosts.addAll(items.stream().map(item -> new ItemWithCost(item.getName(), item.getQuantity(), price, (price* item.getQuantity()))).toList());
    }

    @Test
    public void itTestsAddingOrder(){
        double totalCost = 0.0;
        for(int i=0 ; i < itemCosts.size() ; i++){
            totalCost+=itemCosts.get(i).getCost();
        }
        OrderSummary orderSummaryExpected = new OrderSummary(1, totalCost, itemCosts);

        List<String> itemsNamesList  = items.stream().map(Item::getName).toList();
        when(inventoryService.validateInventory(itemsNamesList)).thenReturn(true);

        when(inventoryService.getPrices(items.stream().map((Item::getName)).toList())).thenReturn(costMap);
        when(orderRepository.addOrder(items)).thenReturn(new Order(1, items));
        Assert.assertEquals(ordersService.addOrder(items), orderSummaryExpected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itThrowsExceptionIfInvalidItemAddedToOrder(){
        List<String> itemsNamesList  = items.stream().map(Item::getName).toList();
        when(inventoryService.validateInventory(itemsNamesList)).thenReturn(false);
        ordersService.addOrder(items);
       /* OrderSummary orderSummaryExpected = new OrderSummary(1, 0.0, itemCosts);
        Assert.assertEquals(ordersService.addOrder(items), orderSummaryExpected);*/
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setName("banana");
        item.setQuantity(10);

        Item item2 = new Item();
        item.setName("orange");
        item.setQuantity(15);

        items.add(item2);
        items.add(item);

        return items;
    }
}
