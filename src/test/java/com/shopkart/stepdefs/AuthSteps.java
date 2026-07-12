package com.shopkart.stepdefs;

import com.shopkart.data.builders.CustomerBuilder;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.LoginPage;
import io.cucumber.java.en.Given;

public final class AuthSteps {
    private final WorldContext world;
    public AuthSteps(WorldContext world)
    {
        this.world = world;
    }
    @Given("{string} is logged in")
    public void login(String persona) {
        CustomerBuilder.Session session = CustomerBuilder.aCustomer().named(persona).login();
        if (persona.equals("alice")) world.alice = session; else if (persona.equals("bob")) world.bob = session;
    }
    @Given("{string} signs into the web shop")
    public void uiLogin(String persona) {
        new LoginPage().open().signIn(persona + "@shopkart.test", com.shopkart.data.secret.Secrets.get(persona + ".password"));
    }
}
