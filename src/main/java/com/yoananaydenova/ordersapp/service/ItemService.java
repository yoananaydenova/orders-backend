package com.yoananaydenova.ordersapp.service;

import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

public interface ItemService {

    ItemDTO createItem(AddItemDTO addItemDTO);

    Page<ItemDTO> findAllItemsInPage(PageRequest pageRequest);

    Item findById(Long id);

    ItemDTO findItemDTOById(Long id);

    ItemDTO updateItemById(Long id, AddItemDTO addItemDTO);

    String deleteItemById(Long id);

    List<ItemDTO> getAvailableItems();
}
