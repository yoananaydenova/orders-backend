package com.yoananaydenova.ordersapp.controller;

import com.yoananaydenova.ordersapp.model.dtos.AddOrderDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderDTO;
import com.yoananaydenova.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // 201 created
    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody AddOrderDTO addOrderDTO){
        return ResponseEntity.ok(orderService.createOrder(addOrderDTO));
    }

    // 200 ok
    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getSingleOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody AddOrderDTO addOrderDTO){
        return ResponseEntity.ok(orderService.updateOrderById(id, addOrderDTO));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.deleteOrderById(id));
    }

}
