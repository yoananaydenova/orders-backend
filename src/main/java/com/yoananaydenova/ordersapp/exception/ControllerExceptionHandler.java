package com.yoananaydenova.ordersapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ErrorResponse itemNotFound(ItemNotFoundException ex) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ItemQuantityException.class)
    public ErrorResponse lessItemQuantity(ItemQuantityException ex) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
