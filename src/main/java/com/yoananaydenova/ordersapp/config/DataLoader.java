package com.yoananaydenova.ordersapp.config;

import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;

    public DataLoader(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.save(new Item("Item_1", 1, 1.0));
        itemRepository.save(new Item("Item_2", 2, 2.0));
    }
}