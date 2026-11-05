package com.annjose;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class SelfHealer {

    private final Page page;
    private final List<String> healLog = new ArrayList<>();

    public SelfHealer(Page page) {
        this.page = page;
    }

    public Locator find(String name, String... selectors) {
        for (int i = 0; i < selectors.length; i++) {
            Locator locator = page.locator(selectors[i]);

            if (locator.count() == 1) {
                if (i > 0) {
                    String message = "HEALED " + name + ": " + selectors[0] + " -> " + selectors[i];
                    healLog.add(message);
                    System.out.println(message);
                }
                return locator;
            }
        }
        throw new AssertionError("Could not find " + name);
    }

    public List<String> getHealLog() {
        return healLog;
    }
}