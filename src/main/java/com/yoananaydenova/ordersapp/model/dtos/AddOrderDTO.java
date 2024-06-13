package com.yoananaydenova.ordersapp.model.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

public record AddOrderDTO(
        Long orderId,
        Set<OrderItemDTO> items
) {

}
