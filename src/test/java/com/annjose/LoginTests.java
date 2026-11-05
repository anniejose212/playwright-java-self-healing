package com.annjose;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void brokenLoginButtonIsHealed() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            SelfHealer healer = new SelfHealer(page);
            LoginPage login = new LoginPage(page, healer).open();
            login.loginAs("standard_user", "secret_sauce");

            Assert.assertEquals(login.pageTitle(), "Products");
            Assert.assertEquals(healer.getHealLog().size(), 1);
        }
    }
}