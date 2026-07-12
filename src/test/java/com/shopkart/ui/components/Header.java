package com.shopkart.ui.components;

import static com.codeborne.selenide.Selenide.$x;

public final class Header {
    public Header openCart() {
        $x("//button[normalize-space()='Cart']").click();
        return this;
    }
}
