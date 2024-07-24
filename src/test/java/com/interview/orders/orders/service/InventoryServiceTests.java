package com.interview.orders.orders.service;

import com.interview.orders.orders.core.Inventory;
import com.interview.orders.orders.data.InventoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTests {
    InventoryService inventoryService;

    @Mock
    InventoryRepository inventoryRepository;

    @Before
    public void setUp() {
        inventoryService = new InventoryService(inventoryRepository);
    }

    @Test
    public void itTestsAddingInventory(){
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryService.addInventory(inventoryList);
        verify(inventoryRepository, times(1)).addItems(inventoryList);
    }

    @Test
    public void itTestsGetInventory(){
        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory = new Inventory(1, "apple", 1.1);
        inventoryList.add(inventory);
        when(inventoryRepository.getInventory()).thenReturn(inventoryList);
        Assert.assertEquals(inventoryService.getInventory(), inventoryList);
    }

    @Test
    public void itTestsValidatesInventory(){
        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory = new Inventory(1, "apple", 1.1);
        inventoryList.add(inventory);
        when(inventoryRepository.getInventory()).thenReturn(inventoryList);
        List<String> items = new ArrayList<>();
        items.add("apple");
        Assert.assertTrue(inventoryService.validateInventory(items));
    }

    @Test
    public void itTestsValidatesInventoryFailure(){
        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory = new Inventory(1, "apple", 1.1);
        inventoryList.add(inventory);
        when(inventoryRepository.getInventory()).thenReturn(inventoryList);
        List<String> items = new ArrayList<>();
        items.add("apple");
        items.add("orange");
        Assert.assertFalse(inventoryService.validateInventory(items));
    }

    @Test
    public void itTestsGetPricesForInventoryItemsByName(){
        Map<String, Double> itemPrices = new HashMap<>();
        itemPrices.put("apple", 1.1);
        itemPrices.put("orange", 0.2);

        List<String> items = new ArrayList<>();
        items.add("apple");
        items.add("orange");
        when(inventoryRepository.getPrices(items)).thenReturn(itemPrices);
        inventoryService.getPrices(items);
        Assert.assertEquals(inventoryService.getPrices(items).get("apple"), Double.valueOf(1.1));
        Assert.assertEquals(inventoryService.getPrices(items).get("orange"), Double.valueOf(0.2));

    }

}
