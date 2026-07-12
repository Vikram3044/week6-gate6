package com.shopkart.stepdefs;

import com.shopkart.api.OrderClient;
import com.shopkart.data.builders.OrderBuilder;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class OrderSteps {
    private final WorldContext world;
    public OrderSteps(WorldContext world) {
        this.world = world;
    }
    @Given("{string} has a PLACED order")
    public void existingOrder(String persona) {
        world.orderId = OrderBuilder.anOrder().forCustomer(persona.equals("alice") ? world.alice : world.bob).build();
    }
    @When("^\\\"([^\\\"]+)\\\" requests GET /api/orders/\\{that id\\}$")
    public void otherGetsOrder(String persona) {
        world.response = new OrderClient(persona.equals("alice") ? world.alice.token() : world.bob.token()).get(world.orderId);
    }
    @When("she places the order")
    public void place() {
        world.response = new OrderClient(world.alice.token()).place(world.cartId, "42 Automation Lane, Bengaluru 560001");
        world.orderId = ((Number) world.response.path("orderId")).longValue(); }
    @When("she cancels the order")
    public void cancel() {
        world.response = new OrderClient(world.alice.token()).cancel(world.orderId);
    }
    @Then("the response status is {int}")
    public void status(int status) {
        world.response.then().statusCode(status);
    }
    @Then("^GET /api/orders/\\{that id\\} returns PLACED and totalPaise (\\d+)$")
    public void orderApi(int total) {
        var response = new OrderClient(world.alice.token()).get(world.orderId);
        response.then().statusCode(200); assertEquals("PLACED", response.path("status")); assertEquals(total, ((Number) response.path("totalPaise")).intValue()); }
    @Then("the orders table has exactly one PLACED row for alice")
    public void dbProof() {
        assertEquals(1, OrderRepository.placedCount(world.orderId, world.alice.customerId(), 99800));
    }
    @Then("the order total is {int} paise")
    public void responseTotal(int total) {
        assertEquals(total, ((Number) world.response.path("totalPaise")).intValue());
    }
}
