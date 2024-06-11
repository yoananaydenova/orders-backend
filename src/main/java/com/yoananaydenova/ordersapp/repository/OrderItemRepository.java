package com.yoananaydenova.ordersapp.repository;

import com.yoananaydenova.ordersapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
