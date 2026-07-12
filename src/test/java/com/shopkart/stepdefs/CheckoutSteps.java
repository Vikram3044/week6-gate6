package com.shopkart.stepdefs;

import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.OrderPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/** UI-only journey actions; API/DB validation remains in the other thin glue classes. */
public final class CheckoutSteps {
    private final WorldContext world;
    private OrderPage confirmation;
    public CheckoutSteps(WorldContext world) { this.world = world; }
    @Given("she adds {int} x {string} \\({int} paise each\\) through the UI")
    public void addThroughUi(int quantity, String sku, int unitPrice) {
        String productName = switch (sku) { case "SKU-BAG" -> "Metro Carryall"; default -> throw new IllegalArgumentException("No UI product mapping for " + sku); };
        for (int i = 0; i < quantity; i++) new HomePage().product(productName).addToCart();
    }
    @When("she checks out with a valid address")
    public void checkout() { confirmation = new HomePage().cart().checkout().placeOrder("42 Automation Lane, Bengaluru 560001"); world.orderId = Long.parseLong(confirmation.id()); }
    @Then("the order confirmation shows status {string}")
    public void confirmationStatus(String status) { confirmation.statusIs(status); }
}
