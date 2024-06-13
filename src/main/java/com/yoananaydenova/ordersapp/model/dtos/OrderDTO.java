package com.yoananaydenova.ordersapp.model.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record OrderDTO(
        Long orderId,

         LocalDateTime createdOn,

         LocalDateTime updatedOn,

         BigDecimal totalAmount,

        List<OrderItemDTO>items,

        List<String> failedAddedItems){
}
