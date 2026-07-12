package com.shopkart.data.builders;

import com.shopkart.api.OrderClient;

public final class OrderBuilder {
    private CustomerBuilder.Session session;
    private String sku = "SKU-BAG";
    private int quantity = 1;
    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }
    public OrderBuilder forCustomer(CustomerBuilder.Session value) {
        session = value;
        return this;
    }
    public OrderBuilder withItem(String value, int count) {
        sku = value;
        quantity = count;
        return this;
    }
    public long build() {
        long cart = CartBuilder.aCartFor(session).withItem(sku, quantity).build();
        return ((Number) new OrderClient(session.token()).place(cart, "42 Automation Lane, Bengaluru 560001").then().statusCode(201).extract().path("orderId")).longValue();
    }
}
