package com.yoananaydenova.ordersapp.model.dtos;

public record ItemDTO (
        long id,
        String name,
        double price,
        int quantity
){
}
