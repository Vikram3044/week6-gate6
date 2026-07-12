package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

abstract class ApiClient {
    protected RequestSpecification spec(String token) {
        RequestSpecBuilder builder = new RequestSpecBuilder().setBaseUri(AppConfig.apiUrl()).setContentType("application/json");
        if (token != null && !token.isBlank()) builder.addHeader("Authorization", "Bearer " + token);
        return given().spec(builder.build());
    }
}
