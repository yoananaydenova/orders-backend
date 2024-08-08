package com.yoananaydenova.ordersapp.controller;

import com.yoananaydenova.ordersapp.exception.ItemNotFoundException;
import com.yoananaydenova.ordersapp.model.dtos.AddItemDTO;
import com.yoananaydenova.ordersapp.model.dtos.ItemDTO;
import com.yoananaydenova.ordersapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 201 created
    @PostMapping("/item")
    public ResponseEntity<ItemDTO> createItem(@RequestBody AddItemDTO addItemDTO,  UriComponentsBuilder ucb){
        ItemDTO savedItemDTO = itemService.createItem(addItemDTO);
        URI locationOfNewCashCard = ucb
                .path("item/{id}")
                .buildAndExpand(savedItemDTO.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }


    // 200 ok
    // all-items?page=0&size=5&sort=name,asc
    @GetMapping("/all-items")
    private ResponseEntity<List<ItemDTO>> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "name")));
        Page<ItemDTO> page =itemService.findAllItemsInPage(pageRequest);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/available-items")
    public ResponseEntity<List<ItemDTO>> getAvailableItems(){
        return ResponseEntity.ok(itemService.getAvailableItems());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDTO> getSingleItemById(@PathVariable Long id){
       return ResponseEntity.ok(itemService.findItemDTOById(id));
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
