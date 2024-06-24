package com.yoananaydenova.ordersapp.service.impl;

import com.yoananaydenova.ordersapp.exception.ItemNotFoundException;
import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.ItemDTO;
import com.yoananaydenova.ordersapp.repository.ItemRepository;
import com.yoananaydenova.ordersapp.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO createItem(AddItemDTO addItemDTO) {

        final Item item = itemRepository.save(new Item(addItemDTO.name(), addItemDTO.availableQuantity(), addItemDTO.currentPrice()));
        return convertItemIntoDTO(item);
    }

    private ItemDTO convertItemIntoDTO(Item item) {
        return new ItemDTO(item.getItemId(), item.getName(), item.getCurrentPrice(), item.getAvailableQuantity());
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream().map(this::convertItemIntoDTO).collect(Collectors.toList());
    }

    @Override
    public ItemDTO findItemDTOById(Long id) {
        return convertItemIntoDTO(findById(id));
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
    @Override
    public ItemDTO updateItemById(Long id, AddItemDTO newItem) {
       final Item item = findById(id);

        item.setName(newItem.name());
        item.setAvailableQuantity(newItem.availableQuantity());
        item.setCurrentPrice(newItem.currentPrice());

        return convertItemIntoDTO(itemRepository.save(item));
    }

    @Override
    public String deleteItemById(Long id) {
        if (!itemRepository.existsById(id)){
            throw new ItemNotFoundException(id);
        }

        itemRepository.deleteById(id);

        return """
               Item with id %s has been successfully deleted!""".formatted(id);
    }
}
