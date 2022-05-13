package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage extends WebDriverManager {
    public WebElement productName(){
        return  remoteWebdriver.get().findElement(By.cssSelector("#sp-title"));
    }

    public WebElement productPrice(){
        return  remoteWebdriver.get().findElement(By.cssSelector("#sp-price-lowPrice"));
    }
}
