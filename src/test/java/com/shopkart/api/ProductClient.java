package com.shopkart.api;

import io.restassured.response.Response;

public final class ProductClient extends ApiClient {
    public Response search(String query) {
        return spec(null).queryParam("q", query)
                .get("/products");
    }
}
