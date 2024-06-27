package com.yoananaydenova.ordersapp.model.dtos;

public record OrderItemDTO(

        long id,
        String name,
        double price,
        int quantity
) {
}
