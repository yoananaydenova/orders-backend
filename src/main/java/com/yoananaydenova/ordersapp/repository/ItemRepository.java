package com.yoananaydenova.ordersapp.repository;

import com.yoananaydenova.ordersapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
