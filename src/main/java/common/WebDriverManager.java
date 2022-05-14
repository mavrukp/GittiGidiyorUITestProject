package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    public static ThreadLocal<RemoteWebDriver> remoteWebdriver= new ThreadLocal<RemoteWebDriver>();

    public void chromeWebdriverSet(){
        String fileSeperator = System.getProperty("file.separator");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                fileSeperator + "src" + fileSeperator + "main" + fileSeperator + "resources" + fileSeperator +
                "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        remoteWebdriver.set(new ChromeDriver(chromeOptions));
        remoteWebdriver.get().manage().deleteAllCookies();
        remoteWebdriver.get().manage().window().maximize();
        //remoteWebdrive.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    public void chromeWebdriverQuit(){
        remoteWebdriver.get().quit();

    }
}
