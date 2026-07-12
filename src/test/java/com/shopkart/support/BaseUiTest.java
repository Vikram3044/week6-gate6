package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.shopkart.config.AppConfig;
import io.cucumber.java.Before;

public final class BaseUiTest {
    @Before(order = 0)
    public void configureBrowser() {
        Configuration.headless = AppConfig.headless();
        Configuration.baseUrl = AppConfig.baseUrl();
        Configuration.timeout = 10_000;
        Configuration.savePageSource = false;
    }
}
