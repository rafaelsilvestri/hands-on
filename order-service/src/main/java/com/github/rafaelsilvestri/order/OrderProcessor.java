package com.github.rafaelsilvestri.order;

public class OrderProcessor {

    public Order processOrder(Order order) throws OrderValidationException {
        // validate
        OrderValidator.validate(order);
        // persist
         return null;
    }

}
