package com.uom.cps3230.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void loginUserFromLoginPage(String username, String password){
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameInput.sendKeys(username);

        // Wait for the password input field to be visible and then input the password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
    }

    public void logoutUserFromLoginPage(){
        By logoutLinkLocator = By.xpath("//a[contains(@href, 'customer-logout')]");
        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(logoutLinkLocator));

        logoutLink.click();
    }
}
