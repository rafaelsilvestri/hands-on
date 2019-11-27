package com.github.rafaelsilvestri.order;

public class OrderValidationException extends Exception {

    public OrderValidationException() {
    }

    public OrderValidationException(String message) {
        super(message);
    }
}
