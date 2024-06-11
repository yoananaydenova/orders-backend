package com.yoananaydenova.ordersapp.service;

import com.yoananaydenova.ordersapp.model.Order;
import com.yoananaydenova.ordersapp.model.dtos.AddOrderDTO;

import java.util.List;


public interface OrderService {

    AddOrderDTO createOrder(AddOrderDTO addOrderDTO);

    List<Order> getAllOrders();
}
