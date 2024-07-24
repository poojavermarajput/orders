package com.interview.orders.orders.controller;

import com.interview.orders.orders.core.Item;
import com.interview.orders.orders.core.OrderSummary;
import com.interview.orders.orders.service.OrdersService;
import org.springframework.web.bind.annotation.*;

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


}