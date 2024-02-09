package com.uom.cps3230;

import com.uom.cps3230.enums.ScottsStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;
import org.checkerframework.checker.units.qual.A;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class ScottsTester implements FsmModel {
//System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
//    driver = new ChromeDriver();
//    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private static WebDriver driver;
    private static WebDriverWait wait;
    ScottsOperator sut = new ScottsOperator();
    ScottsStates state = ScottsStates.HOME;
    private int shoppingCartCount = 0;
    private Deque<ScottsStates> stateHistory = new ArrayDeque<>();

    private boolean isLoggedInUser = false;
    private boolean firstAccess = true;

    private boolean isProductSearchedFor = false;

//    @BeforeClass
//    public static void setUpClass() {
//        // System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
//       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kris Saliba\\Desktop\\UoM\\yr3\\testing\\chromedriver.exe");
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @AfterEach
//    public static void tearDown() {
//
//            driver.quit();
//
//    }
    @Override
    public Object getState() {
        return state;
    }

    @Override
    public void reset(boolean testing) {
        System.out.println("Resetting...");
        state = ScottsStates.HOME;
        stateHistory.clear();
        shoppingCartCount = 0;
        isLoggedInUser = false;
        if(testing){
            sut = new ScottsOperator();
            sut.openScottsWebsite();
        }

    }
    public boolean navigateHomeGuard() {

        //if the current state is CART and the last state was HOME, then the cart was closed, thus going back to HOME
        boolean isCorrectLastState = !stateHistory.isEmpty() && stateHistory.peek() == ScottsStates.HOME;
        return (state == ScottsStates.LOGIN
                || state == ScottsStates.SEARCH_RESULTS)
                || (state == ScottsStates.SHOPPING_CART && isCorrectLastState);
    }
                //}
    public @Action void navigateHome(){
        System.out.println("Navigating to Home");
        sut.navigateHome();
        state = ScottsStates.HOME;
        Assert.assertTrue(sut.isHomePage());
    }


    public boolean loginUserGuard() {
       // boolean isCorrectLastState = !stateHistory.isEmpty() && stateHistory.peek() == ScottsStates.LOGIN;
        return ((state == ScottsStates.HOME)
                || state == ScottsStates.SEARCH_RESULTS)&& !isLoggedInUser;
               // || state == ScottsStates.SHOPPING_CART) && !isLoggedInUser);
    }
    public @Action void loginUser(){
        System.out.println("Logging in...");
        sut.loginUser("salibakris03@gmail.com", "testingCps3230");
        state = ScottsStates.LOGIN;
        isLoggedInUser = true;

        Assert.assertTrue(sut.isLoggedInUser());
    }
    public boolean logoutUserGuard() {
        return ((state == ScottsStates.HOME
        || state == ScottsStates.SEARCH_RESULTS) && isLoggedInUser);
        // || state == ScottsStates.SHOPPING_CART) && !isLoggedInUser);
    }
    public @Action void logoutUser(){
        System.out.println("Logging out...");

        sut.logoutUser();
        state = ScottsStates.LOGIN;
        isLoggedInUser = false;
        Assert.assertTrue(!sut.isLoggedInUser());
    }

    public boolean searchForGuard() { return ((state == ScottsStates.HOME
                                            || state == ScottsStates.SEARCH_RESULTS
                                            || state == ScottsStates.LOGIN
                                            || state == ScottsStates.SHOPPING_CART));}
    public @Action void searchFor(){
        System.out.println("Searching for product...");
        sut.searchProduct("bread");
        state = ScottsStates.SEARCH_RESULTS;
        isProductSearchedFor = true;
        Assert.assertTrue(sut.isSearchResults());
    }

//    public boolean addProductToCartGuard(){return state == ScottsStates.SEARCH_RESULTS;}
//    public @Action void addProductToCart(){
//        System.out.println("Adding product to cart...");
//        sut.addFirstProductToCart();
//        state = ScottsStates.SHOPPING_CART;
//        shoppingCartCount++;
//        System.out.println("cartcount:" +shoppingCartCount +" actual number:" + sut.getNumberOfItemsInCart());
//        Assert.assertEquals(shoppingCartCount,sut.getNumberOfItemsInCart());
//        Assert.assertTrue(false);
//
//    }
    public boolean openShoppingCartGuard() {
        return (state == ScottsStates.LOGIN
                || state == ScottsStates.SEARCH_RESULTS
                || state == ScottsStates.HOME);
                //|| state == ScottsStates.PRODUCT_DETAILS;
    }
    public @Action void openShoppingCart() {
        System.out.println("Opening shopping cart...");
        stateHistory.push(state);
        sut.openShoppingCart();
        state = ScottsStates.SHOPPING_CART;
    }

    public boolean closeShoppingCartGuard() {
        return state == ScottsStates.SHOPPING_CART && !stateHistory.isEmpty();
    }
    public @Action void closeShoppingCart() {
        System.out.println("Closing shopping cart...");
        sut.closeShoppingCart();
        state = stateHistory.pop();
    }
    public boolean removeProductFromCartGuard(){return state == ScottsStates.SHOPPING_CART && shoppingCartCount != 0;}
//    public @Action void removeProductFromCart(){
//        System.out.println("Removing item from cart...");
//        sut.removeFirstProductFromCart();
//        shoppingCartCount--;
//        state = ScottsStates.SHOPPING_CART;
//    }
//    public boolean checkoutGuard(){return state == ScottsStates.SHOPPING_CART && shoppingCartCount != 0;}
//    public @Action void checkout(){
//        System.out.println("Checkout");
//        sut.clickCheckout();
//        state = ScottsStates.CHECKOUT;
//    }
    @AfterEach
    public void tearDown(){
        sut.closeBrowser();
    }

    @Test
    public void ScottsOperatorModelRunner() {
        final GreedyTester tester = new GreedyTester(new ScottsTester());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());

        tester.generate(25);
        tester.printCoverage();
    }

}
