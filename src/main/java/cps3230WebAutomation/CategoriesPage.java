package cps3230WebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoriesPage {
    WebDriver driver;
    WebDriverWait wait;

    public CategoriesPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public String getCurrentCategoryFromSidePanel(){
        WebElement currentCatElement = driver.findElement(By.cssSelector("li.current-cat > a"));
        return currentCatElement.getText();
    }
    public int countProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("ul.products > li.product.type-product"));

        return products.size();
    }
    public void clickProductByIndex(int index) {
        List<WebElement> products = driver.findElements(By.cssSelector("ul.products > li.product.type-product"));

        // Checking if index is in range
        if (index >= 0 && index < products.size()) {

            products.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid product index: " + index);
        }
    }

    public boolean checkProductImageAndPrice( ) {

        //Image
        List<WebElement> imageElements = driver.findElements(By.cssSelector("a[data-elementor-open-lightbox='no'] > img.attachment-woocommerce_single"));
        boolean isImagePresent = !imageElements.isEmpty();

        //Price
        List<WebElement> priceElements = driver.findElements(By.cssSelector("bdi > span.woocommerce-Price-currencySymbol"));
        boolean isPricePresent = !priceElements.isEmpty();

        return isImagePresent && isPricePresent;
    }



}
