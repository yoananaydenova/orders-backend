package com.yoananaydenova.ordersapp.controller;

import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.Order;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public Item createItem(@RequestBody AddItemDTO addItemDTO){
        return itemService.createItem(addItemDTO);
    }

    @GetMapping("/items")
    public List<Item> getAllOrders(){
        return itemService.getAllItems();
    }
}
