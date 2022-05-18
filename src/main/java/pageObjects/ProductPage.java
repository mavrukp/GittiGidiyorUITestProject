package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage {

    private RemoteWebDriver driver;

    public ProductPage(RemoteWebDriver remoteWebdriver){
        driver = remoteWebdriver;
    }

    public WebElement productName(){
        //return  driver.findElement(By.cssSelector("#sp-title"));
        return  driver.findElement(By.xpath("//*[@id='sp-title']"));
    }

    public WebElement productPrice(){
        return  driver.findElement(By.cssSelector("#sp-price-lowPrice"));
    }

    public WebElement addToBasketButton(){
     //return  driver.findElement(By.cssSelector("#add-to-basket"));
        return  driver.findElement(By.xpath("//*[@id='add-to-basket']"));

    }

    public void clickAddToBasketButton(WebElement button){
        Actions builder = new Actions(driver);
        Action mouseOverProdct = builder
                .moveToElement(button)
                .click().build();
        mouseOverProdct.perform();
    }

    public void mouseHoverBasketButton(WebElement button){
        Actions builder = new Actions(driver);
        Action mouseOverProdct = builder
                .moveToElement(button).build();
        mouseOverProdct.perform();
    }

    public WebElement basketButton(){
        return  driver.findElement(By.xpath("//a[@class='header-cart-hidden-link']"));

    }

    public void clickBasketButton(){
        WebElement button =  driver.findElement(By.xpath("//a[@class='header-cart-hidden-link']"));
        //return  driver.findElement(By.linkText("https://www.gittigidiyor.com/sepetim"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", button);

    }
}
