package com.shopkart.support;

import io.restassured.RestAssured;
import com.shopkart.config.AppConfig;
import io.cucumber.java.Before;

public final class BaseApiTest {
    @Before(order = 1)
    public void configureApi() { RestAssured.baseURI = AppConfig.apiUrl(); }
}
