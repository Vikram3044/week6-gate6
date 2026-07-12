package com.shopkart.api;

import io.restassured.response.Response;
import java.util.Map;

public final class CartClient extends ApiClient {
    private final String token;
    public CartClient(String token) {
        this.token = token;
    }
    public Response create() {
        return spec(token).post("/carts");
    }
    public Response add(long cartId, String sku, int quantity) {
        return spec(token).body(Map.of("sku", sku, "qty", quantity)).post("/carts/" + cartId + "/items");
    }
    public Response get(long cartId) {
        return spec(token).get("/carts/" + cartId);
    }
}
