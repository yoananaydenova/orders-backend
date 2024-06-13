package com.yoananaydenova.ordersapp.controller;

import com.yoananaydenova.ordersapp.model.Order;
import com.yoananaydenova.ordersapp.model.dtos.AddOrderDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderDTO;
import com.yoananaydenova.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:5173")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody AddOrderDTO addOrderDTO){
        return orderService.createOrder(addOrderDTO);
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }
}
