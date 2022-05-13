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
        chromeWebdriverSet();
    }

    public WebElement searchInputBox(){
        return  remoteWebdriver.get().findElement(By.cssSelector("input[placeholder='Keşfetmeye Bak']"));
    }

    public WebElement searchFindButton(){
        return  remoteWebdriver.get().findElement(By.cssSelector("button[type='submit']"));
    }

    public WebElement pageListItems(int indx){
        return  remoteWebdriver.get().findElements(By.cssSelector("li[data-testid='pagination-list-item']")).get(indx);
    }

    public List<WebElement> productListItems(){
        //return  remoteWebdriver.get().findElements(By.cssSelector("ul li.sc-1nx8ums-0"));
        return  remoteWebdriver.get().findElements(By.xpath("//ul//li[contains(@class,'sc-1nx8ums')]"));
    }

    public void selectProduct(WebElement product){
        Actions builder = new Actions(remoteWebdriver.get());
        Action mouseOverProdct = builder
                .moveToElement(product)
                .click().build();
        mouseOverProdct.perform();
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
