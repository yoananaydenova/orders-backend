package com.yoananaydenova.ordersapp.repository;

import com.yoananaydenova.ordersapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
