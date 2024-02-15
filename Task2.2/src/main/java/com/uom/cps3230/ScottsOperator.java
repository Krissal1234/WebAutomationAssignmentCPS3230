package com.uom.cps3230;

import com.uom.cps3230.pageObjects.HomePage;
import com.uom.cps3230.pageObjects.LoginPage;
import com.uom.cps3230.pageObjects.NavigationBar;
import com.uom.cps3230.pageObjects.SearchPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class ScottsOperator {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    NavigationBar nav;
    LoginPage loginPage;
    SearchPage searchPage;

    private boolean isLoggedInUser = false;
    private boolean isLoggedOutUser = false;

    private boolean isSearchResults = false;
    private boolean isProductSelected = false;
    private boolean isHomePage = false;
    private boolean isShoppingCartOpen = false;

    public ScottsOperator() {
        //PageObject definitions
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        nav = new NavigationBar(driver, wait);
        loginPage = new LoginPage(driver, wait);
        searchPage = new SearchPage(driver, wait);
    }
    public void openScottsWebsite(){
        homePage.navigateToHomePage();
    }

    public void navigateHome() {
        WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.elementor-item[href='https://www.scotts.com.mt/']")));
        homeLink.click();
        if (verifyHomePage()){
            isHomePage = true;
            isSearchResults = false;
        }
    }
    private boolean verifyHomePage(){
        try {
            WebElement homeLinkActive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.elementor-item.elementor-item-active[href='https://www.scotts.com.mt/']")));
            return homeLinkActive.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isHomePage(){
        return isHomePage;
    }

     void loginUser(String username, String password) {
        nav.navigateToLoginPage();
        loginPage.loginUserFromLoginPage(username, password);

        verifyLogin();
    }
    private void verifyLogin() {
        try {
            //Logout link displays after login
            WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-navigation-link--customer-logout a")));
            isLoggedInUser = logoutLink.isDisplayed();
        } catch (TimeoutException e) {
            isLoggedInUser = false;
            isSearchResults = false;
            isShoppingCartOpen = false;
        }
    }
    public boolean isLoggedInUser() {
        return isLoggedInUser;
    }

    public void logoutUser(){
       nav.navigateToLoginPage();
       loginPage.logoutUserFromLoginPage();

       isLoggedInUser = false;
       isSearchResults = false;
       isShoppingCartOpen = false;


    }



    public void searchProduct(String product) {

        nav.navigateToSearchPage();
        searchPage.searchForProductFromSearchPage(product);
        if(verifySearchProduct()){
            isSearchResults = true;
            isHomePage = false;
            isShoppingCartOpen = false;
        }
    }
    private boolean verifySearchProduct(){
        try{
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.woocommerce-LoopProduct-link.woocommerce-loop-product__link")));

        List<WebElement> productLinks = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link.woocommerce-loop-product__link"));

        return !productLinks.isEmpty() && productLinks.size() > 1;
        }catch (Exception ex){
            System.out.println("Exception occurred during product search verification");
            return false;
        }
    }

    public boolean isSearchResults() {
        return isSearchResults;
    }


    public void addFirstProductToCart() {
        searchPage.addFirstProductToCartFromSearchResults();
        if(verifyOpenShoppingCart()){
            isShoppingCartOpen = true;
        }
    }

    public void openShoppingCart() {
        nav.navigateToShoppingCart();
        if(verifyOpenShoppingCart()){
            isShoppingCartOpen = true;
        }
    }

    private boolean verifyOpenShoppingCart(){
        try {
            WebElement shoppingCartTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-sidebar-title")));
            boolean isShoppingCartPresent = shoppingCartTitle.getText().equalsIgnoreCase("Shopping cart");

            return isShoppingCartPresent;
        } catch (Exception e) {
            System.out.println("Shopping cart verification failed: " + e.getMessage());
            return false;
        }
    }

    public void closeShoppingCart() {

       nav.closeShoppingCart();
       isShoppingCartOpen = false;
    }
    public boolean isShoppingCartOpen(){
        return isShoppingCartOpen;
    }

    public void closeBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {}
        }
    }

}
