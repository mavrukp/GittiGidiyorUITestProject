package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class HomePage extends WebDriverManager {

    public HomePage(){
    }

    public WebElement searchInputBox(){
        return remoteWebdriver.get().findElement(By.xpath("//input[starts-with(@class,'sc-4995aq-0')]"));
    }

    public WebElement searchFindButton(){
        return remoteWebdriver.get().findElement(By.cssSelector("button[type='submit']"));
    }

    public WebElement pageListItems(int indx){
        return  remoteWebdriver.get().findElements(By.cssSelector("li[data-testid='pagination-list-item']")).get(indx);
    }

    public WebElement cookieWarnCloseButton(){
        return  remoteWebdriver.get().findElement(By.xpath("//span[text()='Kapat']"));
    }

    public List<WebElement> productListItems(){
        return  remoteWebdriver.get().findElements(By.xpath("//ul//li[contains(@class,'sc-1nx8ums')]"));
    }

    public void selectProduct(WebElement product){
        Actions builder = new Actions(remoteWebdriver.get());
        Action mouseOverProdct = builder
                .moveToElement(product)
                .click().build();
        mouseOverProdct.perform();
    }

    public void scrollToProduct(WebElement product){
        Actions a = new Actions(remoteWebdriver.get());
        a.moveToElement(product);
        a.perform();
    }

    public void scrollToEndOfPage() {
        ((JavascriptExecutor) remoteWebdriver.get())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void navigateToMainURL(){
        remoteWebdriver.get().get("https://www.gittigidiyor.com/");
    }

    public String getPageURLText(){
        return remoteWebdriver.get().getCurrentUrl();
    }
}
