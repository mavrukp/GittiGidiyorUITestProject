package pageObjects;

import common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasketPage extends WebDriverManager {
    public WebElement productBasketPrice(){
        return  remoteWebdriver.get().findElement(By.cssSelector(".total-price-box .total-price"));
    }

    public void selectCount(String count){
        WebElement selectElement = remoteWebdriver.get().findElement(By.cssSelector("select.amount"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue(count);
    }

    public WebElement productCount(){
        return  remoteWebdriver.get().findElement(By.cssSelector("select.amount"));
    }

    public WebElement deleteButton(){
        return  remoteWebdriver.get().findElement(By.cssSelector(".btn-delete"));
    }

    public void clickDeleteProductButton(WebElement button){
        JavascriptExecutor js = (JavascriptExecutor)remoteWebdriver.get();
        js.executeScript("arguments[0].click();", button);

    }

    public WebElement emptyBasketInfoMessage(){
        return  remoteWebdriver.get().findElement(By.xpath("//h2[text()='Sepetinizde ürün bulunmamaktadır.']"));
    }

    public WebElement removedFromBasketMsg(){
        return  remoteWebdriver.get().findElement(By.xpath("//span[text()='Ürün Sepetten Silindi']"));
    }

    public WebElement updatedProductCountInfoMsg(){
        return  remoteWebdriver.get().findElement(By.xpath("//p[text()='Ürünün adeti güncellendi']"));
    }
}
