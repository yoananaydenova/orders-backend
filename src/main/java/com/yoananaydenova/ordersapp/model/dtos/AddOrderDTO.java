package com.yoananaydenova.ordersapp.model.dtos;


import java.util.Set;

public record AddOrderDTO(
        Long orderId,
        Set<OrderItemDTO> items
) {

}
