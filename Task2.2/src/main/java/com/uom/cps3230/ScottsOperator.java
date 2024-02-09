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

//    private boolean ;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;
//    private boolean isLoggedInUser;


    public ScottsOperator() {
        //  System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
      //  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kris Saliba\\Desktop\\UoM\\yr3\\testing\\chromedriver.exe");

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
        }
    }
    public boolean isLoggedInUser() {
        return isLoggedInUser;
    }

    public void logoutUser(){
       nav.navigateToLoginPage();
       loginPage.logoutUserFromLoginPage();

       if(verifyLogout()) {
           isLoggedInUser = false;
           isSearchResults = false;
       }

    }
    private boolean verifyLogout(){
        try {
            //Login link displays after logout
            WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("button.woocommerce-button.button.woocommerce-form-login__submit.wp-element-button[name='login'][value='Log in']")));

            return loginButton != null;
        } catch (Exception e) {
            return false;
        }
    }


    public void searchProduct(String product) {

        nav.navigateToSearchPage();
        searchPage.searchForProductFromSearchPage(product);
        if(verifySearchProduct()){
            isSearchResults = true;
            isHomePage = false;
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
//
//    public void selectFirstProduct() {
//        List<WebElement> products = driver.findElements(By.cssSelector("ul.products > li.product.type-product"));
//
//        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
//        firstProduct.click();
//    }

    public void addFirstProductToCart() {
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("a.loop-add-to-cart-btn"));

        if (addToCartButtons.size() > 0) {
            WebElement firstAddToCartButton = addToCartButtons.get(0);

            WebElement clickableAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));

            clickableAddToCartButton.click();
        }
    }
//    //Cart must be open to use this method
//    public int getNumberOfItemsInCart() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.woocommerce-mini-cart-item")));
//
//        List<WebElement> cartItems = driver.findElements(By.cssSelector("li.woocommerce-mini-cart-item"));
//
//        return cartItems.size();
//    }
//
    public void openShoppingCart() {
        WebElement shoppingCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.shopping-bag-button[href*='cart']")));
        shoppingCartButton.click();
    }

    public void closeShoppingCart() {

    WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("close-cart-sidebar-btn")));
    closeButton.click();
    }

//    public void clickCheckout(){
//        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.checkout.wc-forward.wp-element-button")));
//        checkoutButton.click();
//    }
//    public void removeFirstProductFromCart() {
//        WebElement quantityInput = driver.findElement(By.cssSelector("li.woocommerce-mini-cart-item:first-child .qty"));
//        int quantity = Integer.parseInt(quantityInput.getAttribute("value"));
//
//        if (quantity > 1) {
//            // If the quantity is greater than 1, find and click the "minus" button
//            WebElement minusButton = driver.findElement(By.cssSelector("li.woocommerce-mini-cart-item:first-child .mini-cart-product-qty[data-qty='minus']"));
//            minusButton.click();
//        } else {
//            // If the quantity is 1, find and click the "remove" button (×)
//            WebElement removeButton = driver.findElement(By.cssSelector("li.woocommerce-mini-cart-item:first-child .remove"));
//            removeButton.click();
//        }
//    }
    public void closeBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {}
        }
    }

}
