package com.interview.orders.orders.service;
import com.interview.orders.orders.core.*;
import com.interview.orders.orders.core.offer.Offer;
import com.interview.orders.orders.data.OrderRepository;
import com.interview.orders.orders.service.offer.OfferFactory;
import com.interview.orders.orders.service.offer.OfferInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class OrdersService {

    private final OfferService offerService;
    InventoryService inventoryService;
    OrderRepository orderRepository;
    OfferFactory offerFactory;

    public OrdersService(InventoryService inventoryService, OrderRepository orderRepository, OfferService offerService, OfferFactory offerFactory) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.offerService = offerService;
        this.offerFactory = offerFactory;
    }

    public OrderSummary addOrder(List<Item> itemList) {
        validateOrder(itemList);
        List<ItemWithCost> itemCosts = getItemCosts(itemList);
        return orderRepository.addOrder(OrderSummary.generateOrderSummary(itemCosts));
    }

    private List<ItemWithCost> getItemCosts(List<Item> itemList) {
        Map<String, Double> itemPrices = inventoryService.getPrices(itemList.stream().map((Item::getName)).toList());
        Map<String, Offer> offersByInventoryName = offerService.getOfferByInventoryNames(itemList.stream().map(Item::getName).toList());
        return calculateItemPriceByOffer(offersByInventoryName, itemList, itemPrices);
    }

    private List<ItemWithCost> calculateItemPriceByOffer(Map<String, Offer> offersByInventoryName, List<Item> itemList, Map<String, Double> itemPrices) {
        // for each inventory get offer, then call offerFactory to get offer and calculate offer

        List<ItemWithCost> itemWithCosts = new ArrayList<>();

        for (Item item : itemList) {
            if(offersByInventoryName.get(item.getName())!=null) {
                OfferInterface offerInterface = offerFactory.getOffer(offersByInventoryName.get(item.getName()).getOfferType());
                double cost = offerInterface.calculateOfferPrice(item.getQuantity(), itemPrices.get(item.getName()));
                ItemWithCost itemWithCost = new ItemWithCost(item.getName(), item.getQuantity(), itemPrices.get(item.getName()), cost);
                itemWithCosts.add(itemWithCost);
            } else {
                double cost = item.getQuantity()* itemPrices.get(item.getName());
                ItemWithCost itemWithCost = new ItemWithCost(item.getName(), item.getQuantity(), itemPrices.get(item.getName()), cost);
                itemWithCosts.add(itemWithCost);
            }
        }
        return itemWithCosts;

    }

    public OrderSummary getOrder(int orderId) {
        return orderRepository.getOrder(orderId);
    }

    public Collection<OrderSummary> getOrders() {
        return orderRepository.getOrders();
    }

    private void validateOrder(List<Item> itemList) {
        List<String> inventoryItems = itemList.stream().map(Item::getName).toList();
        if(!inventoryService.validateInventory(inventoryItems)){
            throw new IllegalArgumentException("Invalid Item in order");
        }
    }
}
