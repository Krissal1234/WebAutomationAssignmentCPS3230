package com.uom.cps3230.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineShoppingPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    public OnlineShoppingPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        js = (JavascriptExecutor) driver;
    }

    public void clickCategory(String category) {
        category = category.toLowerCase();
        if(category.contains("&")){
            category = category.replaceAll("\\s+", "").replace("&", "-");
        }else{
            category = category.toLowerCase().replaceAll("\\s+", "-");
        }

        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href*='/product-category/" + category + "/']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", categoryElement);
        categoryElement.click();
    }

    public void closeWaterPopup()  {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'dialog-close-button') and .//i[contains(@class, 'eicon-close')]]")
        ));
        js.executeScript("arguments[0].click();", closeButton);
//        closeButton.click(); // Seems to be unreliable --mention in report and mention implications on testability
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.dialog-widget-content.dialog-lightbox-widget-content")));
    }

}
