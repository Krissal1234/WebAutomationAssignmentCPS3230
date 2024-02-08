package com.uom.cps3230;

import com.uom.cps3230.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScottsOperator {

    public ScottsOperator(WebDriver driver, WebDriverWait wait) {
        HomePage homePage = new HomePage(driver, wait);
    }
}
