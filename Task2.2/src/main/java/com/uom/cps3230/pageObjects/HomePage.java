package com.uom.cps3230.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToHomePage() {
        driver.get("https://www.scotts.com.mt/");
        driver.manage().window().maximize();
    }
}
