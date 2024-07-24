package com.interview.orders.orders.data;

import com.interview.orders.orders.core.Inventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InventoryRepositoryTests {

    InventoryRepository inventoryRepository;

    @Before
    public void setUp(){
        inventoryRepository = new InventoryRepository();
    }

    @Test
    public void itTestsAddAndRetrievalOfInventory(){
        List<Inventory> retrievedInventory = inventoryRepository.getInventory();
        Assert.assertTrue(retrievedInventory.isEmpty());

        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.setName("apple");
        inventory.setPrice(10);
        inventoryList.add(inventory);

        inventoryRepository.addItems(inventoryList);

        List<Inventory> retrievedInventoryAfterAdding = inventoryRepository.getInventory();
        Assert.assertEquals(retrievedInventoryAfterAdding.size(), 1);
        Assert.assertEquals(retrievedInventoryAfterAdding.get(0).getName(), "apple");
        Assert.assertEquals(retrievedInventoryAfterAdding.get(0).getPrice(), 10, 0.02);
    }

    @Test
    public void itTestsPriceRetrievalOfInventory(){
        Assert.assertTrue(inventoryRepository.getPrices(List.of("banana")).isEmpty());
        Assert.assertTrue(inventoryRepository.getPrices(List.of("apple")).isEmpty());
        Assert.assertTrue(inventoryRepository.getPrices(List.of("orange")).isEmpty());

        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.setName("apple");
        inventory.setPrice(10);
        inventoryList.add(inventory);

        Inventory inventory2 = new Inventory();
        inventory2.setName("orange");
        inventory2.setPrice(20);
        inventoryList.add(inventory2);

        inventoryRepository.addItems(inventoryList);
        Assert.assertEquals(inventoryRepository.getPrices(List.of("apple")).get("apple"), Double.valueOf(10));
        Assert.assertEquals(inventoryRepository.getPrices(List.of("orange")).get("orange"), Double.valueOf(20));
        Assert.assertTrue(inventoryRepository.getPrices(List.of("banana")).isEmpty());

    }

}
