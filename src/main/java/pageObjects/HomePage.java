package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class HomePage {

    private RemoteWebDriver driver;

    public HomePage(RemoteWebDriver remoteWebdriver){
        driver = remoteWebdriver;
    }

    public WebElement searchInputBox(){
        return driver.findElement(By.xpath("//input[starts-with(@class,'sc-4995aq-0')]"));
    }

    public WebElement searchFindButton(){
        return  driver.findElement(By.cssSelector("button[type='submit']"));
    }

    public WebElement pageListItems(int indx){
        return  driver.findElements(By.cssSelector("li[data-testid='pagination-list-item']")).get(indx);
    }

    public WebElement cookieWarnCloseButton(){
        return  driver.findElement(By.xpath("//span[text()='Kapat']"));
    }

    public List<WebElement> productListItems(){
        //return  driver.findElements(By.cssSelector("ul li.sc-1nx8ums-0"));
        return  driver.findElements(By.xpath("//ul//li[contains(@class,'sc-1nx8ums')]"));
    }

    public void selectProduct(WebElement product){
        Actions builder = new Actions(driver);
        Action mouseOverProdct = builder
                .moveToElement(product)
                .click().build();
        mouseOverProdct.perform();
    }

    public void scrollToProduct(WebElement product){
        Actions a = new Actions(driver);
        a.moveToElement(product);
        a.perform();
    }

    public void scrollToEndOfPage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void navigateToMainURL(){
        driver.get("https://www.gittigidiyor.com/");
    }

    public String getPageURLText(){
        return driver.getCurrentUrl();
    }
}
