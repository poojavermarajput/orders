package com.interview.orders.orders.data;

import com.interview.orders.orders.core.Inventory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InventoryRepository {

    List<Inventory> inventories = new ArrayList<>();

    public static AtomicInteger inventoryId = new AtomicInteger(0);

    public List<Inventory> getInventory(){
        return inventories;
    }

    public void addItems(List<Inventory> inventoryList){
        inventoryList.forEach(item -> {
            inventoryId.incrementAndGet();
            Inventory inventory = new Inventory(inventoryId.get(), item.getName(), item.getPrice());
            inventories.add(inventory);
        });
    }

    public Map<String, Double> getPrices(List<String> itemNames) {
        List<Inventory> inventoryToRetrieve = inventories.stream().filter(item-> itemNames.contains(item.getName())).toList();

        return inventoryToRetrieve.stream().collect(
                Collectors
                        .toMap(
                                Inventory::getName,
                                Inventory::getPrice));

    }
}
