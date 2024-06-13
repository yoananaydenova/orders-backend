package com.yoananaydenova.ordersapp.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id) {
        super("""
              The order with id: %s does not exist!""".formatted(id));
    }
}
