package com.interview.orders.orders.service;
import com.interview.orders.orders.core.*;
import com.interview.orders.orders.data.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class OrdersService {

    InventoryService inventoryService;
    OrderRepository orderRepository;

    public OrdersService(InventoryService inventoryService, OrderRepository orderRepository) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
    }

    public OrderSummary addOrder(List<Item> itemList) {
        validateOrder(itemList);
        Order order = orderRepository.addOrder(itemList);

        List<ItemWithCost> itemCosts = getItemCosts(itemList);

        return OrderSummary.generateOrderSummary(order.getOrderNumber(), itemCosts);

    }

    private List<ItemWithCost> getItemCosts(List<Item> itemList) {
        Map<String, Double> itemPrices = inventoryService.getPrices(itemList.stream().map((Item::getName)).toList());
        return itemList.stream().map(item -> new ItemWithCost(item.getName(),item.getQuantity(), itemPrices.get(item.getName()), (item.getQuantity() * itemPrices.get(item.getName()) ))).toList();
    }

    private void validateOrder(List<Item> itemList) {
        List<String> inventoryItems = itemList.stream().map(Item::getName).toList();
        if(!inventoryService.validateInventory(inventoryItems)){
            throw new IllegalArgumentException("Invalid Item in order");
        }
    }
}
