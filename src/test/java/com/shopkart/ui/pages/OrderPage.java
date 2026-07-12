package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$x;

public final class OrderPage {
    public OrderPage statusIs(String status) {
        $x("//*[@data-field='order-status']")
                .shouldHave(Condition.text(status));
        return this;
    }
    public String id() {
        $x("//*[@data-field='order-status']").shouldBe(Condition.visible);
        String url = com.codeborne.selenide.WebDriverRunner.url();
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
