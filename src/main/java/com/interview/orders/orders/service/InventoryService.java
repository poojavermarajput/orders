package com.interview.orders.orders.service;

import com.interview.orders.orders.core.Inventory;
import com.interview.orders.orders.data.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addInventory(List<Inventory> items) {
        inventoryRepository.addItems(items);
    }

    public List<Inventory> getInventory(){
        return inventoryRepository.getInventory();
    }

    public boolean validateInventory(List<String> items){
         List<String> inventory = inventoryRepository.getInventory().stream().map(Inventory::getName).toList();
        return inventory.containsAll(items);
    }

    public Map<String, Double> getPrices(List<String> itemNames) {
        return inventoryRepository.getPrices(itemNames);
    }
}
