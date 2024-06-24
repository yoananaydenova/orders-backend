package com.yoananaydenova.ordersapp.model.dtos;

public record ItemDTO (
        long itemId,
        String itemName,
        double price,
        int quantity
){
}
