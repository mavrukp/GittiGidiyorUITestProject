package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitElement extends WebDriverManager{

    public void waitElementToVisible(WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(remoteWebdriver.get())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(60))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementToBeClickable(WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(remoteWebdriver.get())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(60))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementInvisibilty(WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(remoteWebdriver.get())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(60))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) remoteWebdriver.get()).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(remoteWebdriver.get(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public Boolean elementIsExist(WebElement element){
        return element.isDisplayed();
    }

    public void implicitlyWait(int duration){
        remoteWebdriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
    }
}
