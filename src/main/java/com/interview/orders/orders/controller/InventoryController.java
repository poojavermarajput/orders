package com.interview.orders.orders.controller;

import com.interview.orders.orders.core.Inventory;
import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class InventoryController {

    InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/item")
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    @PostMapping("/item-add")
    public void addInventory(@RequestBody List<Inventory> inventoryList) {
        inventoryService.addInventory(inventoryList);
    }
}
