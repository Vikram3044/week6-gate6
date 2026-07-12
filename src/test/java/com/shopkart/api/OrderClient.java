package com.shopkart.api;

import io.restassured.response.Response;
import java.util.Map;

public final class OrderClient extends ApiClient {
    private final String token;
    public OrderClient(String token)
    {
        this.token = token;
    }
    public Response place(long cartId, String address)
    {
        return spec(token).body(Map.of("cartId", cartId, "address", address))
                .post("/orders");
    }
    public Response get(long orderId)
    {
        return spec(token).get("/orders/" + orderId);
    }
    public Response cancel(long orderId)
    {
        return spec(token).post("/orders/" + orderId + "/cancel");
    }
}
