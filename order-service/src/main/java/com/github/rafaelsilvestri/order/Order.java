package com.github.rafaelsilvestri.order;

import java.util.List;

public class Order {
    private Customer customer;
    private PaymentMethod paymentMethod;
    private List<Product> productList;
    private Address deliveryAddress;
}
