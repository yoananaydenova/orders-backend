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
        // Load initial data into the database
        itemRepository.save(new Item("Hammer", 10, 10.50));
        itemRepository.save(new Item("Pliers", 12, 22.60));
    }
}