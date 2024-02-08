package test.scottsFunctionality;

import cps3230WebAutomation.CategoriesPage;
import cps3230WebAutomation.HomePage;
import cps3230WebAutomation.OnlineShoppingPage;
import cps3230WebAutomation.SearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    SearchPage searchPage;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Mention by placing in the before, fresh state of objects
        homePage = new HomePage(driver,wait);
        shoppingPage = new OnlineShoppingPage(driver, wait);
        categoriesPage = new CategoriesPage(driver,wait);
        searchPage = new SearchPage(driver,wait);

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
        Assertions.assertTrue( categoriesPage.returnProducts() >= numOfProducts);
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



    @When("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTerm(String searchField)  {
        homePage.searchForItem(searchField);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults() throws InterruptedException {

        Assertions.assertFalse(searchPage.getSearchResults().isEmpty());

    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastMinProductCountProductsInTheSearchResults(int minProductCount) {
        Assertions.assertTrue(searchPage.getSearchResults().size() > minProductCount);
    }


}
