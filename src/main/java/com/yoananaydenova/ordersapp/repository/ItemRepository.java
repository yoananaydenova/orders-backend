package com.yoananaydenova.ordersapp.repository;

import com.yoananaydenova.ordersapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

List<Item> findAllByAvailableQuantityGreaterThan(Integer target);
}
