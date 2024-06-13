package com.yoananaydenova.ordersapp.model.dtos;


public record AddItemDTO(
        String name,
        int availableQuantity,
        double currentPrice) {
}
