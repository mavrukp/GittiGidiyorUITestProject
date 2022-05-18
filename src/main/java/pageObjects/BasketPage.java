package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class BasketPage{

    private RemoteWebDriver driver;

    public BasketPage(RemoteWebDriver remoteWebdriver){
        driver = remoteWebdriver;
    }

    public WebElement productBasketPrice(){
        return  driver.findElement(By.cssSelector(".total-price-box .total-price"));
    }

    public void selectCount(String count){
        WebElement selectElement = driver.findElement(By.cssSelector("select.amount"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue(count);
    }

    public WebElement productCount(){
        return  driver.findElement(By.cssSelector("select.amount"));
    }

    public WebElement deleteButton(){
        return  driver.findElement(By.cssSelector(".btn-delete"));
    }

    public void clickDeleteProductButton(WebElement button){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", button);

    }

    public WebElement emptyBasketInfoMessage(){
        //return  driver.findElement(By.xpath("//h2[text()='Sepetinizde ürün bulunmamaktadır.']"));
        return  driver.findElement(By.xpath("//div[contains(@class,'delete-toaster')]//h2"));
    }

    public WebElement removedFromBasketMsg(){
        //return  driver.findElement(By.cssSelector("div.delete-toaster div.toaster-msg-wrapper span"));
        return  driver.findElement(By.xpath("//div[@class='delete-toaster']//div[contains(@class,'toaster-msg-wrapper')]//span"));
    }

    public WebElement updatedProductCountInfoMsg(){
        return  driver.findElement(By.xpath("//p[text()='Ürünün adeti güncellendi']"));
    }
}
