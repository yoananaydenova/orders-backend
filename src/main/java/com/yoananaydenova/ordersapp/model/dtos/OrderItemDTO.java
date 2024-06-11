package com.yoananaydenova.ordersapp.model.dtos;

import java.util.List;

public record OrderItemDTO(

        long itemId,
        String itemName,
        int quantity


) {
}
