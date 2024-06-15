package com.yoananaydenova.ordersapp.service;


import com.yoananaydenova.ordersapp.model.dtos.AddOrderDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderDTO;

import java.util.List;


public interface OrderService {

    OrderDTO createOrder(AddOrderDTO addOrderDTO);

    List<OrderDTO> getAllOrders();

    OrderDTO findById(Long id);

    OrderDTO updateOrderById(Long id, AddOrderDTO addItemDTO);

    String deleteOrderById(Long id);
}
