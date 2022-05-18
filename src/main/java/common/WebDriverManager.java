package common;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;


public class WebDriverManager {
    public RemoteWebDriver remoteWebdriver;

    public  void chromeWebdriverSet(){
        String fileSeperator = System.getProperty("file.separator");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                fileSeperator + "src" + fileSeperator + "main" + fileSeperator + "resources" + fileSeperator +
                "chromedriver.exe");
        ChromeOptions chromeOptions = setChromeOptions();
        remoteWebdriver = new ChromeDriver(chromeOptions);
        //remoteWebdriver.set(new ChromeDriver(chromeOptions));
        remoteWebdriver.manage().deleteAllCookies();
        //remoteWebdrive.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }


    public  void remoteChromeWebdriverSet() {
        ChromeOptions chromeOptions = setChromeOptions();
        String nodeURL = "http://localhost:4444/wd/hub";
        try {
            remoteWebdriver = new RemoteWebDriver(new URL(nodeURL), chromeOptions);
            //remoteWebdriver.set(new RemoteWebDriver(new URL(nodeURL), chromeOptions));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        remoteWebdriver.manage().deleteAllCookies();
    }

    private  ChromeOptions setChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("window-size=1366,768");
        //chromeOptions.addArguments("--start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return chromeOptions;
    }

    public void chromeWebdriverQuit(){
        remoteWebdriver.quit();

    }
}
