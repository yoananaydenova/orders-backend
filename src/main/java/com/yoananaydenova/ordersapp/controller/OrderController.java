package com.yoananaydenova.ordersapp.controller;

import com.yoananaydenova.ordersapp.model.Order;
import com.yoananaydenova.ordersapp.model.dtos.AddOrderDTO;
import com.yoananaydenova.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public AddOrderDTO createOrder(@RequestBody AddOrderDTO addOrderDTO){
        return orderService.createOrder(addOrderDTO);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
