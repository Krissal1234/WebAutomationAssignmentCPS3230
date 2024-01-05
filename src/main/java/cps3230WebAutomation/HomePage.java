package cps3230WebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver,WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;

    }

    public void navigateToHomePage() {
        driver.get("https://www.scotts.com.mt/");
        driver.manage().window().maximize();
//        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }



    public void clickShopOnline(){
        WebElement shopOnlineLink = driver.findElement(By.xpath("//li[@class='menu-item menu-item-type-post_type menu-item-object-page menu-item-1546']/a[text()='Shop Online']"));
        shopOnlineLink.click();
    }

    public void searchForItem(String item){

        // Find the <a> element with href="#search" using XPath
        WebElement searchLink = driver.findElement(By.xpath("//a[@href='#search']"));

        // Click on the search link
        searchLink.click();



    }


}
