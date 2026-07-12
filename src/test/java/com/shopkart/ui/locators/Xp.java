package com.shopkart.ui.locators;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

/** Central, relative, parameterised XPath templates. No absolute or index locators. */
public final class Xp {
    public static final String PRODUCT = "//div[contains(@class,'product')][.//*[normalize-space()='%s']]";
    public static final String ADD_TO_CART = PRODUCT + "//button[normalize-space()='Add to cart']";
    public static final String CART_LINE = "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";
    private Xp() { }
    public static SelenideElement product(String name) {
        return $x(PRODUCT.formatted(name));
    }
    public static SelenideElement addToCart(String name) {
        return $x(ADD_TO_CART.formatted(name));
    }
    public static SelenideElement cartLine(String sku) {
        return $x(CART_LINE.formatted(sku));
    }
}
