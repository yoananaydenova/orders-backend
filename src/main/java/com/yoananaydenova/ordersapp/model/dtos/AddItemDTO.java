package com.yoananaydenova.ordersapp.model.dtos;

import jakarta.persistence.Column;

public record AddItemDTO(String name,
                         int availableQuantity,
                         double currentPrice) {
}
