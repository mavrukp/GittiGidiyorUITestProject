import common.FileReaderWriter;
import common.WaitElement;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.BasketPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

import java.text.Normalizer;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest {

    final static Logger logger = LoggerFactory.getLogger(SearchTest.class);
    private static HomePage homePage;

    @BeforeAll
    public static void setDriver() {
        logger.info("Starting test Thread Number Is " + Thread.currentThread().getId());

        homePage = new HomePage();

    }

    @Test
    @Order(1)
    public void searchTest() throws InterruptedException {
        WaitElement waitElement = new WaitElement();
        homePage.navigateToMainURL();

        Thread.sleep(10000);

        if (homePage.cookieWarnCloseButton().isDisplayed()){
            homePage.cookieWarnCloseButton().click();
        }

        homePage.searchInputBox().sendKeys("Bilgisayar");
        homePage.searchFindButton().click();

        Thread.sleep(15000);

        homePage.scrollToEndOfPage();
        waitElement.waitElementToBeClickable(homePage.pageListItems(1));
        homePage.pageListItems(1).click();

        assertTrue(homePage.getPageURLText().contains("sf=2"));

    }

    @Test
    @Order(2)
    public void selectRandomProduct() throws InterruptedException {
        WaitElement waitElement = new WaitElement();
        ProductPage productPage = new ProductPage();

        Thread.sleep(5000);

        int indx = new Random().nextInt(homePage.productListItems().size()-1);


            Thread.sleep(5000);

        homePage.selectProduct(homePage.productListItems().get(indx));


            Thread.sleep(5000);

        waitElement.waitElementToVisible(productPage.productName());

        String productName = productPage.productName().getText();
        String productPrice = productPage.productPrice().getText();

        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        fileReaderWriter.writeTextToFile(productName + " " + productPrice);
    }

    @Test
    @Order(3)
    public void addRandomProductToBasket() throws InterruptedException {
        WaitElement waitElement = new WaitElement();
        ProductPage productPage = new ProductPage();
        BasketPage basketPage = new BasketPage();

        waitElement.waitElementToBeClickable(productPage.addToBasketButton());

        Thread.sleep(5000);

        productPage.clickAddToBasketButton(productPage.addToBasketButton());

        Thread.sleep(5000);

        productPage.clickBasketButton();

        Thread.sleep(5000);

        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        String productBasketPrice = basketPage.productBasketPrice().getText();
        String readLineFromFile = fileReaderWriter.readFromFile();

        assertTrue(readLineFromFile.contains(productBasketPrice));

    }

    @Test
    @Order(4)
    public void increaseProductCount() throws InterruptedException {
        WaitElement waitElement = new WaitElement();

        BasketPage basketPage = new BasketPage();

        basketPage.selectCount("2");

        assertEquals("2",basketPage.productCount().getAttribute("value"));
        //assertTrue(basketPage.updatedProductCountInfoMsg().isDisplayed());

    }

    @Test
    @Order(5)
    public void cleanBasket() throws InterruptedException {
        WaitElement waitElement = new WaitElement();

        BasketPage basketPage = new BasketPage();

        Thread.sleep(5000);

        basketPage.clickDeleteProductButton(basketPage.deleteButton());

        Thread.sleep(5000);

        String asciiInfoMsgElement = Normalizer.normalize(basketPage.emptyBasketInfoMessage().getText(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        assertTrue(basketPage.removedFromBasketMsg().isDisplayed());

    }

    @AfterAll
    public static void driverQuit(){
        homePage.chromeWebdriverQuit();
    }

}
