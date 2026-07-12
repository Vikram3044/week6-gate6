package com.shopkart.data.builders;

import com.shopkart.api.AuthClient;
import com.shopkart.data.secret.Secrets;

public final class CustomerBuilder {
    private String persona = "alice";
    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }
    public CustomerBuilder named(String persona) {
        this.persona = persona; return this;
    }
    public Session login() {
        var response = new AuthClient().login(persona + "@shopkart.test", Secrets.get(persona + ".password"));
        response.then().statusCode(200); return new Session(response.path("token"), ((Number) response.path("customerId")).longValue(), persona);
    }
    public record Session(String token, long customerId, String persona) { }
}
