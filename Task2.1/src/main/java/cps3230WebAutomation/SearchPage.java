package cps3230WebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    // Mention Page Object Model: Organize your testing code using the Page Object Model.
    // This means creating separate classes for each page of your application, with methods that represent the actions
    // that can be performed on that page.

    public List<WebElement> getSearchResults()  {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("elementor-heading-title")));
//
//        // Checking if the correct page is being displayed
//        WebElement heading = driver.findElement(By.className("elementor-heading-title"));
//        if (!heading.getText().contains("Search Results for:")) {
//            throw new IllegalStateException("This is not the search results page");
//        }#
        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[contains(@class, 'products')]//li[contains(@class, 'product')]")));

        return searchResults;

    }



}
