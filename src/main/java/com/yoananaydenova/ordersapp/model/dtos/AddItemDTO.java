package com.yoananaydenova.ordersapp.model.dtos;


public record AddItemDTO(
        String name,
        int quantity,
        double price) {
}
