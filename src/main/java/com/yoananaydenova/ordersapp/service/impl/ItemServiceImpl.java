package com.yoananaydenova.ordersapp.service.impl;

import com.yoananaydenova.ordersapp.exception.ItemNotFoundException;
import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderItemDTO;
import com.yoananaydenova.ordersapp.repository.ItemRepository;
import com.yoananaydenova.ordersapp.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(AddItemDTO addItemDTO) {
        return itemRepository.save(new Item(addItemDTO.name(), addItemDTO.availableQuantity(), addItemDTO.currentPrice()));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public Item updateItemById(Long id, AddItemDTO newItem) {
       final Item item = findById(id);

        item.setName(newItem.name());
        item.setAvailableQuantity(newItem.availableQuantity());
        item.setCurrentPrice(newItem.currentPrice());

        return itemRepository.save(item);
    }
}
