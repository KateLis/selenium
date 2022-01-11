package PageObjectTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestAddAndDeleteProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void addNewProductsAndThenDeleteThem() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        for (int i = 1; i <= 3; i++) {
            mainPage.openMainPage();
            mainPage.clickOnFirstElement();
            productPage.addProductAndCheckIfCartItemIncreased(i);
        }
        mainPage.getCheckoutCart().click();
        cartPage.checkThatAfterDeleteAllItemsWereDeleted();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}