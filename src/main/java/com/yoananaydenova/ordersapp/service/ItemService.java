package com.yoananaydenova.ordersapp.service;

import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderItemDTO;

import java.util.List;

public interface ItemService {

    Item createItem(AddItemDTO addItemDTO);

    List<Item> getAllItems();

    Item findById(Long id);

    Item updateItemById(Long id, AddItemDTO addItemDTO);

    String deleteItemById(Long id);
}
