package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasketPage extends WebDriverManager {
    public WebElement productBasketPrice(){
        return  remoteWebdriver.get().findElement(By.cssSelector(".total-price-box .total-price"));
    }
}
