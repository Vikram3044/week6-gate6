package com.shopkart.support;

import com.shopkart.data.builders.CustomerBuilder;
import io.restassured.response.Response;

public final class WorldContext {
    public CustomerBuilder.Session alice;
    public CustomerBuilder.Session bob;
    public long cartId;
    public long orderId;
    public Response response;
}
