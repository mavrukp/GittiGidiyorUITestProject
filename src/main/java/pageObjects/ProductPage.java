package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ProductPage extends WebDriverManager {
    public WebElement productName(){
        return  remoteWebdriver.get().findElement(By.xpath("//*[@id='sp-title']"));
    }

    public WebElement productPrice(){
        return  remoteWebdriver.get().findElement(By.cssSelector("#sp-price-lowPrice"));
    }

    public WebElement addToBasketButton(){
        return  remoteWebdriver.get().findElement(By.xpath("//*[@id='add-to-basket']"));

    }

    public void clickAddToBasketButton(WebElement button){
        Actions builder = new Actions(remoteWebdriver.get());
        Action mouseOverProdct = builder
                .moveToElement(button)
                .click().build();
        mouseOverProdct.perform();
    }

    public void mouseHoverBasketButton(WebElement button){
        Actions builder = new Actions(remoteWebdriver.get());
        Action mouseOverProdct = builder
                .moveToElement(button).build();
        mouseOverProdct.perform();
    }

    public WebElement basketButton(){
        return  remoteWebdriver.get().findElement(By.xpath("//a[@class='header-cart-hidden-link']"));

    }

    public void clickBasketButton(){
        WebElement button =  remoteWebdriver.get().findElement(By.xpath("//a[@class='header-cart-hidden-link']"));
        JavascriptExecutor js = (JavascriptExecutor)remoteWebdriver.get();
        js.executeScript("arguments[0].click();", button);

    }
}
