package com.shopkart.ui.pages;

import static com.codeborne.selenide.Selenide.$x;

public final class CheckoutPage {
    public OrderPage placeOrder(String address) {
        $x("//textarea[@name='address']").setValue(address);
        $x("//button[normalize-space()='Place order']").click();
        return new OrderPage(); }
}
