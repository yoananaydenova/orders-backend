package com.yoananaydenova.ordersapp.service.impl;

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
    public Item getItem(OrderItemDTO dto) {
        return itemRepository.findById(dto.itemId())
                .orElseThrow(() -> new NoSuchElementException(
                        """
                                The item "%s" does not exist!""".formatted(dto.itemName())));

    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
