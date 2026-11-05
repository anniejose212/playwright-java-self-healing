package com.annjose;

import com.microsoft.playwright.Page;

public class LoginPage {

    private static final String USERNAME = "#user-name";
    private static final String PASSWORD = "#password";
    private static final String PAGE_TITLE = ".title";
    private static final String LOGIN_BUTTON_BROKEN = "#login-btn-old";
    private static final String LOGIN_BUTTON = "#login-button";
    private static final String LOGIN_BUTTON_DATA_TEST = "[data-test='login-button']";

    private final Page page;
    private final SelfHealer healer;

    public LoginPage(Page page, SelfHealer healer) {
        this.page = page;
        this.healer = healer;
    }

    public LoginPage open() {
        page.navigate("https://www.saucedemo.com");
        return this;
    }

    public void loginAs(String username, String password) {
        page.locator(USERNAME).fill(username);
        page.locator(PASSWORD).fill(password);
        healer.find("Login button", LOGIN_BUTTON_BROKEN, LOGIN_BUTTON, LOGIN_BUTTON_DATA_TEST).click();
    }

    public String pageTitle() {
        return page.locator(PAGE_TITLE).textContent();
    }
}