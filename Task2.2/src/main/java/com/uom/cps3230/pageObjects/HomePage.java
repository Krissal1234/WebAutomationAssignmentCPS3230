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


    public void clickShopOnline(){
        WebElement shopOnlineLink = driver.findElement(By.xpath("//li[@class='menu-item menu-item-type-post_type menu-item-object-page menu-item-1546']/a[text()='Shop Online']"));
        shopOnlineLink.click();
    }

    public void searchForItem(String item){
        //Mention in teh report, I alwasy used id whenever i could
        WebElement searchButton = driver.findElement(By.id("header-search-icon"));
        searchButton.click();

        WebElement searchInput = driver.findElement(By.id("dgwt-wcas-search-input-1"));
        searchInput.sendKeys(item);

        searchInput.sendKeys(Keys.ENTER);
    }



}
