package com.uom.cps3230.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationBar {

    WebDriverWait wait;
    WebDriver driver;
    public NavigationBar(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;

    }

    public void navigateToLoginPage(){
        WebElement myAccountIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.my-account-icon[href='https://www.scotts.com.mt/my-account/']")));
        myAccountIcon.click();
    }
    public void navigateToSearchPage(){
        By searchIconLocator = By.cssSelector("a.elementor-icon[href='#search']");

        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(searchIconLocator));
        searchIcon.click();
    }
}
