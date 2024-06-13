package com.yoananaydenova.ordersapp.exception;

public class ItemQuantityException extends RuntimeException{
    public ItemQuantityException(String itemName, int availableQuantity, int orderItemQuantity) {
        super("""
              The item %s has available quantity %s and is less than ordered item quantity %s!""".formatted(itemName, availableQuantity, orderItemQuantity));
    }
}
