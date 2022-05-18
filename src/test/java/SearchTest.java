import common.FileReaderWriter;
import common.WaitElement;
import common.WebDriverManager;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.BasketPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

import java.text.Normalizer;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTest extends WebDriverManager {


    final static Logger logger = LoggerFactory.getLogger(SearchTest.class);
    private static HomePage homePage;


    @BeforeAll
    public void setDriver() {
        logger.info("Starting test Thread Number Is " + Thread.currentThread().getId());
        if (System.getProperty("webdriver").equals("chrome")){
            chromeWebdriverSet();
        }else if(System.getProperty("webdriver").equals("remote")) {
            remoteChromeWebdriverSet();
        }

        homePage = new HomePage(remoteWebdriver);
        logger.info("remoteWebdriver getSessionId " + remoteWebdriver.getSessionId());

    }

    @Test
    @Order(1)
    public void searchTest() throws InterruptedException {
        logger.info("searchTest  start => " + Thread.currentThread().getName());
        WaitElement waitElement = new WaitElement(remoteWebdriver);
        homePage.navigateToMainURL();

        Thread.sleep(5000);

        try{
            if (homePage.cookieWarnCloseButton().isDisplayed()){
                homePage.cookieWarnCloseButton().click();
            }
        }catch (NoSuchElementException e){
            logger.error("No such element exception on Home Page for cookie warning");
        }finally {
            Thread.sleep(10000);

            homePage.searchInputBox().sendKeys("Bilgisayar");
            homePage.searchFindButton().click();

            homePage.scrollToEndOfPage();
            waitElement.waitElementToBeClickable(homePage.pageListItems(1));
            homePage.pageListItems(1).click();

            assertTrue(homePage.getPageURLText().contains("sf=2"));
        }

        logger.info("searchTest  end => " + Thread.currentThread().getName());

    }

    @Test
    @Order(2)
    public void selectRandomProduct() throws InterruptedException {
        logger.info("selectRandomProduct  start => " + Thread.currentThread().getName());
        WaitElement waitElement = new WaitElement(remoteWebdriver);
        ProductPage productPage = new ProductPage(remoteWebdriver);

        Thread.sleep(5000);

        int indx = new Random().nextInt(homePage.productListItems().size()-1);

        waitElement.waitElementToBeClickable(homePage.productListItems().get(indx));

            Thread.sleep(5000);

        homePage.selectProduct(homePage.productListItems().get(indx));


            Thread.sleep(5000);

        waitElement.waitElementToVisible(productPage.productName());

        String productName = productPage.productName().getText();
        String productPrice = productPage.productPrice().getText();

        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        fileReaderWriter.writeTextToFile(productName + " " + productPrice);
        logger.info("selectRandomProduct  end => " + Thread.currentThread().getName());
    }

    @Test
    @Order(3)
    public void addRandomProductToBasket() throws InterruptedException {
        logger.info("addRandomProductToBasket  start => " + Thread.currentThread().getName());
        WaitElement waitElement = new WaitElement(remoteWebdriver);
        ProductPage productPage = new ProductPage(remoteWebdriver);
        BasketPage basketPage = new BasketPage(remoteWebdriver);

        waitElement.waitElementToBeClickable(productPage.addToBasketButton());

        Thread.sleep(5000);

        productPage.clickAddToBasketButton(productPage.addToBasketButton());

        Thread.sleep(5000);

        productPage.clickBasketButton();

        Thread.sleep(5000);
        waitElement.waitElementToVisible(basketPage.productBasketPrice());

        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        String productBasketPrice = basketPage.productBasketPrice().getText();
        String readLineFromFile = fileReaderWriter.readFromFile();

        assertTrue(readLineFromFile.contains(productBasketPrice));

        logger.info("addRandomProductToBasket  end => " + Thread.currentThread().getName());

    }

    @Test
    @Order(4)
    public void increaseProductCount() throws InterruptedException {
        logger.info("increaseProductCount  start => " + Thread.currentThread().getName());
        WaitElement waitElement = new WaitElement(remoteWebdriver);

        BasketPage basketPage = new BasketPage(remoteWebdriver);

        basketPage.selectCount("2");

        assertEquals("2",basketPage.productCount().getAttribute("value"));
        //assertTrue(basketPage.updatedProductCountInfoMsg().isDisplayed());
        logger.info("increaseProductCount  end => " + Thread.currentThread().getName());

    }

    @Test
    @Order(5)
    public void cleanBasket() throws InterruptedException {
        logger.info("cleanBasket  start => " + Thread.currentThread().getName());
        WaitElement waitElement = new WaitElement(remoteWebdriver);

        BasketPage basketPage = new BasketPage(remoteWebdriver);

        Thread.sleep(5000);

        basketPage.clickDeleteProductButton(basketPage.deleteButton());

        Thread.sleep(5000);

       String asciiInfoMsgElement = Normalizer.normalize(basketPage.emptyBasketInfoMessage().getText(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        assertEquals("Sepetinizde urun bulunmamaktadr.",asciiInfoMsgElement);

        assertTrue(basketPage.removedFromBasketMsg().isDisplayed());
        logger.info("cleanBasket  end => " + Thread.currentThread().getName());

    }

    @AfterAll
    public void driverQuit(){
        chromeWebdriverQuit();
    }

}
