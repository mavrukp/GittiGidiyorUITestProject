import common.FileReaderWriter;
import common.WaitElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.BasketPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

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
    public void searchTest() {
        WaitElement waitElement = new WaitElement();
        homePage.navigateToMainURL();
        homePage.searchInputBox().sendKeys("Bilgisayar");
        homePage.searchFindButton().click();
        homePage.scrollToEndOfPage();
        waitElement.waitElementToBeClickable(homePage.pageListItems(1));
        homePage.pageListItems(1).click();

        assertTrue(homePage.getPageURLText().contains("sf=2"));

    }

    @Test
    @Order(2)
    public void selectRandomProduct() {
        WaitElement waitElement = new WaitElement();
        ProductPage productPage = new ProductPage();
        int indx = new Random().nextInt(homePage.productListItems().size()-1);
        waitElement.implicitlyWait(30);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitElement.waitElementToVisible(homePage.productListItems().get(indx));
        homePage.selectProduct(homePage.productListItems().get(indx));
        waitElement.implicitlyWait(30);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitElement.waitElementToVisible(productPage.productName());

        String productName = productPage.productName().getText();
        String productPrice = productPage.productPrice().getText();

        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        fileReaderWriter.writeTextToFile(productName + " " + productPrice);
    }

    @Test
    @Order(3)
    public void addRandomProductToBasket() {
        WaitElement waitElement = new WaitElement();
        ProductPage productPage = new ProductPage();
        BasketPage basketPage = new BasketPage();

        waitElement.waitElementToBeClickable(productPage.addToBasketButton());
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        productPage.clickAddToBasketButton(productPage.addToBasketButton());

        productPage.clickBasketButton();

        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        String productBasketPrice = basketPage.productBasketPrice().getText();
        String readLineFromFile = fileReaderWriter.readFromFile();

        assertTrue(readLineFromFile.contains(productBasketPrice));

    }

    @AfterAll
    public static void driverQuit(){
        homePage.chromeWebdriverQuit();
    }

}
