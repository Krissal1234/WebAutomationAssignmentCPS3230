package test.scottsFunctionality;

import cps3230WebAutomation.CategoriesPage;
import cps3230WebAutomation.HomePage;
import cps3230WebAutomation.OnlineShoppingPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductCategoriesSteps {

    WebDriver driver;
    HomePage homePage;
    OnlineShoppingPage shoppingPage;
    CategoriesPage categoriesPage;
    WebDriverWait wait;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Mention by placing in the before, fresh state of objects
        homePage = new HomePage(driver,wait);
        shoppingPage = new OnlineShoppingPage(driver, wait);
        categoriesPage = new CategoriesPage(driver,wait);

    }
    @After
    public void tearDown(){
        driver.quit();
    }
    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite()  {
        homePage.navigateToHomePage();

    }

    @When("I visit the online shop website")
    public void iVisitTheOnlineShopWebsite() {
        homePage.clickShopOnline();
        shoppingPage.closeWaterPopup();

    }
    @And("I click on the {string} category")
    public void iClickOnTheCategory(String categoryName)  {
        shoppingPage.clickCategory(categoryName);
    }

    @Then("I should be taken to {string} category")
    public void iShouldBeTakenToCategory(String categoryName) {
        Assertions.assertEquals(categoryName, categoriesPage.getCurrentCategoryFromSidePanel());
    }

    @And("the category should show at least {int} products")
    public void theCategoryShouldShowAtLeastMinProductCountProducts(int numOfProducts) {
        Assertions.assertTrue( categoriesPage.countProducts() >= numOfProducts);
    }
    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
        categoriesPage.clickProductByIndex(0);
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
        //Couldnt check for details becuase not every product has a details page
        //Cant check for AddToCart becuase the button is not present if an item is out of stock
        Assertions.assertTrue(categoriesPage.checkProductImageAndPrice());
    }



}
