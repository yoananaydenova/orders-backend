package com.yoananaydenova.ordersapp.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id){
        super("""
              The item with id %s does not exist!""".formatted(id));
    }
}
