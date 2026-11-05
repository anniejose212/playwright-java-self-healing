# Playwright Java Self-Healing Demo

A small test automation project in Java showing UI tests with Playwright, API tests with Rest Assured, and a simple self-healing locator.

## Tech stack
- Java, Maven, TestNG
- Playwright for Java (UI tests on [SauceDemo](https://www.saucedemo.com))
- Rest Assured (API tests on [Restful-Booker](https://restful-booker.herokuapp.com))

## What it does
- **BookingApiTest** creates a booking through the API and checks the response fields.
- **LoginTests** logs in to SauceDemo using a page object.
- **SelfHealer** keeps the UI test running when a selector breaks.

## How the self-healing works
Each element is given a main selector plus backup selectors. The healer tries them in order and uses the first one that matches exactly one element. If a backup is used, it logs the change:

```
HEALED Login button: #login-btn-old -> #login-button
```

The test keeps running, and the log tells the team which page object selector needs updating. The demo uses a deliberately broken selector (`#login-btn-old`) to show this in action.
## Run it
Requires Java 17+ and Maven.

```
mvn test
```

Or right-click a test class in IntelliJ and choose **Run**.