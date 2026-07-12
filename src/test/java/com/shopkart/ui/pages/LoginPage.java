package com.shopkart.ui.pages;

import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;
import static com.codeborne.selenide.Selenide.$x;

public final class LoginPage {
    public LoginPage open()
    {
        Selenide.open(AppConfig.baseUrl() + "/login");
        return this;
    }
    public HomePage signIn(String email, String password)
    {
        $x("//input[@name='email']").setValue(email);
        $x("//input[@name='password']").setValue(password).pressEnter();
        return new HomePage().showsProduct("Metro Carryall");
    }
}
