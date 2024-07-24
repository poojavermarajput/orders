package com.interview.orders.orders.controller;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.OrderSummary;
import com.interview.orders.orders.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    OrdersService ordersService;

    public OrdersController (OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/order/")
    public OrderSummary addOrder(@RequestBody List<Item> itemList){
        return ordersService.addOrder(itemList);
    }

    @GetMapping("/order/{orderId}")
    public OrderSummary getOrder(@PathVariable int orderId){
        return ordersService.getOrder(orderId);
    }

    @GetMapping("/order")
    public Collection<OrderSummary> getAllOrders(){
        return ordersService.getOrders();
    }


}
