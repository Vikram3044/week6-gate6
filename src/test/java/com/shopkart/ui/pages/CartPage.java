package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.Xp;
import static com.codeborne.selenide.Selenide.$x;

public final class CartPage {
    public CartPage totalShows(String total) {
        $x("//*[@data-role='cart-total']").shouldHave(Condition.text(total));
        return this;
    }
    public CartPage lineTotal(String sku, String total) {
        Xp.cartLine(sku).$x(".//td[contains(@class,'line-total')]").shouldHave(Condition.text(total));
        return this;
    }
    public CheckoutPage checkout() {
        $x("//button[normalize-space()='Checkout']").click();
        return new CheckoutPage();
    }
}
