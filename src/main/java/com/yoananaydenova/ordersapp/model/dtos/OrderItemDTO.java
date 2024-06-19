package com.yoananaydenova.ordersapp.model.dtos;

public record OrderItemDTO(

        long itemId,
        String itemName,
        double price,
        int quantity
) {
}
