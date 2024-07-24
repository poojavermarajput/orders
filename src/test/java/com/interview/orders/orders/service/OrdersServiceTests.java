package com.interview.orders.orders.service;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.ItemWithCost;
import com.interview.orders.orders.core.Order;
import com.interview.orders.orders.core.OrderSummary;
import com.interview.orders.orders.core.offer.Offer;
import com.interview.orders.orders.data.OrderRepository;
import com.interview.orders.orders.service.offer.OfferFactory;
import com.interview.orders.orders.service.offer.OfferInterface;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTests {
    OrdersService ordersService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    InventoryService inventoryService;

    @Mock
    OfferService offerService;

    OfferFactory offerFactory;

    List<Item> items = new ArrayList<>();
    List<ItemWithCost> itemCosts = new ArrayList<>();
    Map<String, Double> priceMap = new HashMap<>();
    double price = 0.65;

    @Before
    public void setUp(){
        offerFactory = new OfferFactory();
        ordersService = new OrdersService(inventoryService, orderRepository, offerService, offerFactory);
        items.addAll(getItems());
         // assuming each item has same price
        items.forEach(item-> priceMap.put(item.getName(), price));
    }

    @Test
    public void itTestsAddingOrder(){
        double totalCost = 0.0;
        itemCosts.addAll(items.stream().map(item -> new ItemWithCost(item.getName(), item.getQuantity(), price, (price* item.getQuantity()))).toList());
        for(int i=0 ; i < itemCosts.size() ; i++){
            totalCost+=itemCosts.get(i).getCost();
        }
        OrderSummary orderSummaryExpected = new OrderSummary(1, totalCost, itemCosts);

        List<String> itemsNamesList  = items.stream().map(Item::getName).toList();
        when(inventoryService.validateInventory(itemsNamesList)).thenReturn(true);

        when(inventoryService.getPrices(items.stream().map((Item::getName)).toList())).thenReturn(priceMap);
        when(orderRepository.addOrder(items)).thenReturn(new Order(1, items));
        Assert.assertEquals(ordersService.addOrder(items), orderSummaryExpected);
    }

    @Test
    public void itTestsAddingOrderWithOffers(){

        List<ItemWithCost> itemsWithCosts = getItemCostsWithOffers();
        OrderSummary orderSummaryExpected = getExpectedOrderSummaryForOffers(itemsWithCosts);

        List<String> itemsNamesList  = items.stream().map(Item::getName).toList();
        when(inventoryService.validateInventory(itemsNamesList)).thenReturn(true);


        Offer orangeOffer = new Offer("orange", "THREE_FOR_PRICE_OF_TWO");
        Offer appleOffer = new Offer("apple", "BUY_ONE_GET_ONE_FREE");

        Map<String, Offer> offerMap = new HashMap<>();
        offerMap.put("apple", appleOffer);
        offerMap.put("orange", orangeOffer);
        //List.of("orange", "apple", "banana")
        when(offerService.getOfferByInventoryNames(anyList())).thenReturn(offerMap);

        when(inventoryService.getPrices(items.stream().map((Item::getName)).toList())).thenReturn(priceMap);
        when(orderRepository.addOrder(items)).thenReturn(new Order(1, items));
        OrderSummary orderSummaryActual = ordersService.addOrder(items);
        Assert.assertEquals(orderSummaryActual, orderSummaryExpected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itThrowsExceptionIfInvalidItemAddedToOrder(){
        List<String> itemsNamesList  = items.stream().map(Item::getName).toList();
        when(inventoryService.validateInventory(itemsNamesList)).thenReturn(false);
        ordersService.addOrder(items);
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        Item item = new Item("banana", 10);

        Item item2 = new Item("orange", 10);

        Item item3 = new Item("apple", 10);

        items.add(item2);
        items.add(item);
        items.add(item3);

        return items;
    }

    private List<ItemWithCost> getItemCostsWithOffers(){
     List<ItemWithCost> itemsWithCosts = new ArrayList<>();
     for(Item item : items){
         ItemWithCost temp;
         double cost;
         int quantity = item.getQuantity();
         double price = priceMap.get(item.getName());
         if(item.getName().equalsIgnoreCase("apple")){

             cost  = (((double) quantity /2)  + (item.getQuantity()%2)) *  price;

         } else if (item.getName().equalsIgnoreCase("orange")){
             cost  = ((((double) quantity /3)*2)  + (item.getQuantity()%3)) *  price;
         } else {
             cost = quantity * price;
         }

         temp = new ItemWithCost(item.getName(), item.getQuantity(), priceMap.get(item.getName()), cost);
         itemsWithCosts.add(temp);
     }
     return itemsWithCosts;
    }

    private OrderSummary getExpectedOrderSummaryForOffers(List<ItemWithCost> itemCosts) {
        double totalCost = 0.0;
        for(int i=0 ; i < itemCosts.size() ; i++){
            totalCost+=itemCosts.get(i).getCost();
        }
        return new OrderSummary(1, totalCost, itemCosts);
    }
}
