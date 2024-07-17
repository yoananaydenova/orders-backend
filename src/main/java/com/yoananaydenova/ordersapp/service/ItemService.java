package com.yoananaydenova.ordersapp.service;

import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.ItemDTO;


import java.util.List;

public interface ItemService {

    ItemDTO createItem(AddItemDTO addItemDTO);

    List<ItemDTO> getAllItems();

    Item findById(Long id);

    ItemDTO findItemDTOById(Long id);

    ItemDTO updateItemById(Long id, AddItemDTO addItemDTO);

    String deleteItemById(Long id);

    List<ItemDTO> getAvailableItems();
}
