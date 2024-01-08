//package test.scottsFunctionality;
//
//import cps3230WebAutomation.CategoriesPage;
//import cps3230WebAutomation.HomePage;
//import cps3230WebAutomation.OnlineShoppingPage;
//import cps3230WebAutomation.SearchPage;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class SearchFunctionalitySteps {
//    WebDriver driver;
//    WebDriverWait wait;
//    HomePage homePage;
//    SearchPage searchPage;
//    CategoriesPage catPage;
//    @Before
//    public void setup(){
//        System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        //Mention by placing in the before, fresh state of objects
//        //Mention Singleton pattern
//        homePage = HomePage.getInstance(driver,wait);
//        searchPage = SearchPage.getInstance(driver,wait);
//        catPage = new CategoriesPage(driver,wait);
//
//    }
//    @After
//    public void tearDown(){
//        driver.quit();
//    }
//
//
//}
