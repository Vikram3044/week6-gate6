package com.shopkart.data.builders;

import com.shopkart.api.CartClient;

public final class CartBuilder {
    private final CustomerBuilder.Session session; private String sku; private int quantity;
    private CartBuilder(CustomerBuilder.Session session) {
        this.session = session;
    }
    public static CartBuilder aCartFor(CustomerBuilder.Session session) {
        return new CartBuilder(session);
    }
    public CartBuilder withItem(String sku, int quantity) {
        this.sku = sku; this.quantity = quantity; return this;
    }
    public long build() {
        CartClient client = new CartClient(session.token());
        long id = ((Number) client.create().then().statusCode(201).extract().path("cartId")).longValue();
        if (sku != null) client.add(id, sku, quantity).then().statusCode(200); return id; }
}
