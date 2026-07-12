package com.shopkart.stepdefs;

import com.shopkart.api.CartClient;
import com.shopkart.api.ProductClient;
import com.shopkart.data.builders.CartBuilder;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CartSteps {
    private final WorldContext world;
    public CartSteps(WorldContext world) { this.world = world; }
    @When("the catalogue is searched for {string}")
    public void searchApi(String query) {
        world.response = new ProductClient().search(query);
    }
    @Then("the API response contains {string}")
    public void apiContains(String text) {
        world.response.then().statusCode(200); assertTrue(world.response.asString().contains(text));
    }
    @Then("the home page shows {string}")
    public void homeShows(String name) {
        new HomePage().showsProduct(name);
    }
    @Given("{string} has a cart with {int} x {string}")
    public void cartWithItem(String persona, int quantity, String sku) {
        world.cartId = CartBuilder.aCartFor(session(persona)).withItem(sku, quantity).build();
    }
    @When("she requests her cart")
    public void readCart() {
        world.response = new CartClient(world.alice.token()).get(world.cartId);
    }
    @Then("its totalPaise is {int}")
    public void total(int total) {
        world.response.then().statusCode(200);
        assertEquals(total, ((Number) world.response.path("totalPaise")).intValue());
    }
    @When("she adds {int} x {string} to her cart")
    public void addToCart(int quantity, String sku) {
        world.response = new CartClient(world.alice.token()).add(world.cartId, sku, quantity);
    }
    private com.shopkart.data.builders.CustomerBuilder.Session session(String persona) {
        return persona.equals("alice") ? world.alice : world.bob;
    }
}
