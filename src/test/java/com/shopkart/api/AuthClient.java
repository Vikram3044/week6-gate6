package com.shopkart.api;

import io.restassured.response.Response;
import java.util.Map;

public final class AuthClient extends ApiClient {
    public Response login(String email, String password) {
        return spec(null).body(Map.of("email", email, "password", password))
                .post("/auth/login");
    }
}
