package com.yoananaydenova.ordersapp.controller;

import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.ItemDTO;
import com.yoananaydenova.ordersapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public ItemDTO createItem(@RequestBody AddItemDTO addItemDTO){
        return itemService.createItem(addItemDTO);
    }

    @GetMapping("/items")
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/info-items")
    public List<ItemDTO> getAllItemsById(@RequestParam List<Long> ids){
        return itemService.getAllItemsById(ids);
    }

    @GetMapping("/item/{id}")
    public ItemDTO getSingleItemById(@PathVariable Long id){
       return itemService.findItemDTOById(id);
    }

    @PutMapping("/item/{id}")
    public ItemDTO updateItem(@PathVariable Long id, @RequestBody AddItemDTO addItemDTO){
       return itemService.updateItemById(id, addItemDTO);
    }

    @DeleteMapping("/item/{id}")
    public String deleteItem(@PathVariable Long id){
        return itemService.deleteItemById(id);
    }

}
