package com.uom.cps3230;

import com.uom.cps3230.enums.ScottsStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;
import org.junit.Assert;
import org.junit.Test;
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
    WebDriver driver;
    WebDriverWait wait;
    ScottsOperator sut = new ScottsOperator();
    ScottsStates state = ScottsStates.HOME;
    private int shoppingCartCount = 0;
    private Deque<ScottsStates> stateHistory = new ArrayDeque<>();

    private boolean isLoggedInUser = false;
    private boolean isLoggedOutUser = false;

    private boolean isProductSearchedFor = false;


    @Override
    public Object getState() {
        return state;
    }

    @Override
    public void reset(boolean testing) {
        state = ScottsStates.HOME;
        stateHistory.clear();
        shoppingCartCount = 0;
        if(testing){
            System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            sut = new ScottsOperator();
        }

    }
    public boolean navigateHomeGuard() {
        //if the current state is CART and the last state was HOME, then the cart was closed, thus going back to HOME
        boolean isCorrectLastState = !stateHistory.isEmpty() && stateHistory.peek() == ScottsStates.HOME;
        return (state == ScottsStates.LOGIN
                || state == ScottsStates.SEARCH_RESULTS
                || (state == ScottsStates.SHOPPING_CART && isCorrectLastState))
                && !sut.isLoggedInUser();}
    public @Action void navigateHome(){
    state = ScottsStates.HOME;
    sut.navigateHome();
    }
//We are going to assume login before interacting with the site
    public boolean loginUserGuard() {
        // If the current state is SHOPPING_CART and the last state was LOGIN - indicates that the shopping cart was closed
        boolean isCorrectLastState = !stateHistory.isEmpty() && stateHistory.peek() == ScottsStates.LOGIN;
        return (state == ScottsStates.HOME
                || state == ScottsStates.SEARCH_RESULTS
                || (state == ScottsStates.SHOPPING_CART && isCorrectLastState))
                && !sut.isLoggedInUser();
    }
    public @Action void loginUser(){
            sut.loginUser("salibakris03@gmail.com", "testingCps3230");
            state = ScottsStates.LOGIN;
            isLoggedInUser = true;
            isLoggedOutUser = false;

            Assert.assertTrue(sut.isLoggedInUser());
            Assert.assertFalse(sut.isLoggedOutUser());
    }

    public boolean searchForGuard() { return (state == ScottsStates.HOME
                                            || state == ScottsStates.SEARCH_RESULTS
                                            || state == ScottsStates.LOGIN
                                            || state == ScottsStates.SHOPPING_CART) && isLoggedInUser;}
    public @Action void searchFor(){
    sut.searchProduct("bread");
    state = ScottsStates.SEARCH_RESULTS;
    isProductSearchedFor = true;
    Assert.assertTrue(sut.isProductSearchedFor());
    }

//    public boolean selectFirstProductGuard(){return state == ScottsStates.SEARCH_RESULTS && isLoggedInUser;}
//    public @Action void selectFirstProduct(){
//
//        state = ScottsStates.PRODUCT_DETAILS;
//    }

    public boolean addProductToCartGuard(){return state == ScottsStates.SEARCH_RESULTS && isLoggedInUser;}
    public @Action void addProductToCart(){


        state = ScottsStates.SHOPPING_CART;
        shoppingCartCount++;
    }
    public boolean openShoppingCartGuard() {
        return state == ScottsStates.LOGIN
                || state == ScottsStates.SEARCH_RESULTS
                || state == ScottsStates.HOME;
                //|| state == ScottsStates.PRODUCT_DETAILS;
    }
    public @Action void openShoppingCart() {
        stateHistory.push(state);
        state = ScottsStates.SHOPPING_CART;
    }

    public boolean closeShoppingCartGuard() {
        return state == ScottsStates.SHOPPING_CART && !stateHistory.isEmpty();
    }
    public @Action void closeShoppingCart() {
        state = stateHistory.pop();
    }
    public boolean removeProductFromCartGuard(){return state == ScottsStates.SHOPPING_CART && shoppingCartCount != 0;}
    public @Action void removeProductFromCart(){

    }

    @Test
    public void BulbOperatorModelRunner() {
        final GreedyTester tester = new GreedyTester(new ScottsTester());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());

        tester.generate(500);
        tester.printCoverage();


    }

}
