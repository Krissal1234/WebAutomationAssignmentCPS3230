package test.scottsFunctionality;

import cps3230WebAutomation.CategoriesPage;
import cps3230WebAutomation.HomePage;
import cps3230WebAutomation.OnlineShoppingPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchFunctionalitySteps {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Mention by placing in the before, fresh state of objects
        homePage = new HomePage(driver,wait);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
//    @When("Given I am a user of the website")
//    public void iAmAUserOfTheWebsite(){
//    homePage.navigateToHomePage();
//    }
    @When("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTerm(String searchField)  {
        homePage.searchForItem("test0");
        //Thread.sleep(2222);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults() {
    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastMinProductCountProductsInTheSearchResults(int minProductCount) {
    }
}
