package com.uom.cps3230.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    // Mention Page Object Model: Organize your testing code using the Page Object Model.
    // This means creating separate classes for each page of your application, with methods that represent the actions
    // that can be performed on that page.

    public void searchForProductFromSearchPage(String product){
        WebElement searchInput = driver.findElement(By.id("dgwt-wcas-search-input-1"));

        searchInput.clear();

        searchInput.sendKeys(product);

        searchInput.sendKeys(Keys.ENTER);
    }


}
