package com.uom.cps3230;

import com.uom.cps3230.pageObjects.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class ScottsOperator {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;

    private boolean isLoggedInUser = false;
    private boolean isLoggedOutUser = false;

    private boolean isProductSearchedFor = false;
    private boolean isProductSelected = false;

//    private boolean ;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;



    public ScottsOperator(){
        System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);

    }
    public void navigateHome(){
        WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.elementor-item[href='https://www.scotts.com.mt/']")));
        homeLink.click();
    }
   public void loginUser(String username,String password)  {
        homePage.navigateToHomePage();

            //Navigate to login page
           WebElement myAccountIcon = wait.until(ExpectedConditions.elementToBeClickable(
                   By.cssSelector("a.my-account-icon[href='https://www.scotts.com.mt/my-account/']")));
           myAccountIcon.click();


           WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
           usernameInput.sendKeys(username);

           // Wait for the password input field to be visible and then input the password
           WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
           passwordInput.sendKeys(password);

           // Find and click the login button
           WebElement loginButton = driver.findElement(By.name("login"));
           loginButton.click();


           isLoggedInUser = true;
           isLoggedOutUser = false;

    }
    public boolean isLoggedInUser(){
        return isLoggedInUser;
    }
    public boolean isLoggedOutUser(){
        return isLoggedOutUser;
    }

    public void searchProduct(String product){
        WebElement searchButton = driver.findElement(By.id("header-search-icon"));
        searchButton.click();

        WebElement searchInput = driver.findElement(By.id("dgwt-wcas-search-input-1"));
        searchInput.sendKeys(product);

        searchInput.sendKeys(Keys.ENTER);
        isProductSearchedFor = true;
    }
    public boolean isProductSearchedFor(){
        return isProductSearchedFor;
    }
    public void selectFirstProduct(){
        List<WebElement> products = driver.findElements(By.cssSelector("ul.products > li.product.type-product"));

        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
        firstProduct.click();
    }
   public void addFirstProductToCart(){
        List<WebElement> products = driver.findElements(By.cssSelector("ul.products > li.product.type-product"));

            //WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
            //firstProduct.click();
    }


    public void removeFirstProductFromCart(){

    }
}
