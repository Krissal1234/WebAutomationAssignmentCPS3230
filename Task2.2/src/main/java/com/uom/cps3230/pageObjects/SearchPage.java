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
    public void searchForProductFromSearchPage(String product){
        WebElement searchInput = driver.findElement(By.id("dgwt-wcas-search-input-1"));

        searchInput.clear();

        searchInput.sendKeys(product);

        searchInput.sendKeys(Keys.ENTER);
    }

    public void addFirstProductToCartFromSearchResults() {
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("a.loop-add-to-cart-btn"));

        if (addToCartButtons.size() > 0) {
            WebElement firstAddToCartButton = addToCartButtons.get(0);
            WebElement clickableAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));

            clickableAddToCartButton.click();
        }
    }

}
