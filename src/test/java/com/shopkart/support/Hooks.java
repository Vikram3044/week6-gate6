package com.shopkart.support;

import io.cucumber.java.After;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public final class Hooks {
    @After("@ui or @e2e")
    public void closeBrowser() {
        closeWebDriver();
    }
}
