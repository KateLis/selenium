package PageObjectTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ZeroTest extends ZeroBase{

    @Test
    public void addNewProductsAndThenDeleteThem() throws InterruptedException {
        ZeroMainPage mainPage = new ZeroMainPage(driver, wait);
        mainPage.navigate();
        Thread.sleep(1000);
        mainPage.clickOnFirstElement();
        ZeroProductPage productPage = new ZeroProductPage(driver, wait);

        for (int i = 1; i <= 3; i++) {
            mainPage.openMainPage();
            mainPage.clickOnFirstElement();
            productPage.addProductAndCheckIfCartItemIncreased(i);
        }
        mainPage.getCheckoutCart().click();
        ZeroCartPage cartPage = new ZeroCartPage(driver, wait);
        cartPage.checkThatAfterDeleteAllItemsWereDeleted();

    }



}