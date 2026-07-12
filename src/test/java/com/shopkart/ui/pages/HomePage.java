package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.components.ProductCard;
import com.shopkart.ui.locators.Xp;
import static com.codeborne.selenide.Selenide.$x;

public final class HomePage {
    public HomePage showsProduct(String name) {
        Xp.product(name).shouldBe(Condition.visible);
        return this;
    }
    public ProductCard product(String name)
    {
        return new ProductCard(name);
    }
    public CartPage cart()
    {
        $x("//button[normalize-space()='Cart']").click();
        return new CartPage();
    }
}
