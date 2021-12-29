import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Click2 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
         driver = new ChromeDriver();
       // driver = new EdgeDriver();
        wait = new WebDriverWait(driver,10);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void clickAndCheckLeftMenu() throws InterruptedException {
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        //Find left menu elements
        driver.findElement(By.cssSelector("#app-")).click(); //find appearance
        Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));

        WebElement logotype = driver.findElement(By.cssSelector("#doc-logotype")); //logotype
        logotype.click();
        doesItHaveTitle(logotype);

        //CATALOG
        WebElement catalog = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2)"));
        catalog.click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(2)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(3)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(5)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(6)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(7)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(8)")).click();
        driver.findElement(By.cssSelector("ul.docs > li:nth-child(9)")).click();

        //COUNTRIES
        WebElement countries = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(3)"));
        countries.click();

        //CURRENCIES
        WebElement currencies = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(4)"));
        currencies.click();

        //CUSTOMERS
        WebElement customers = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(5)"));
        customers.click();
        WebElement customers2 = driver.findElement(By.cssSelector("ul.docs li#doc-csv"));
        customers2.click();
        WebElement customers3 = driver.findElement(By.cssSelector("ul.docs li#doc-newsletter"));
        customers3.click();

        //GEO ZONES
        WebElement zone = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(6)"));
        zone.click();

        //LANGUAGES
        WebElement lan = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(7)"));
        lan.click();
        driver.findElement(By.cssSelector("ul.docs li#doc-storage_encoding")).click();

        //MODULES
        WebElement module = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(8)"));
        module.click();
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("ul.docs li#doc-customer")).click();
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("ul.docs li#doc-shipping")).click();
        //doc-payment
        driver.findElement(By.cssSelector("ul.docs li#doc-payment")).click();
        //doc-order_total
       // driver.findElement(By.cssSelector("ul.docs li#doc-order_total")).click();
        //doc-order_success
        driver.findElement(By.cssSelector("ul.docs li#doc-order_success")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-order_action")).click();

        //ORDERS
        WebElement order = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(9)"));
        order.click();
        driver.findElement(By.cssSelector("ul.docs li#doc-order_statuses")).click();

        //PAGES
        WebElement page = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(10)"));
        page.click();

        //REPORTS
        WebElement report = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(11)"));
        report.click();
        driver.findElement(By.cssSelector("ul.docs li#doc-most_sold_products")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-most_shopping_customers")).click();//doc-defaults

        //SETTINGS
        WebElement setting = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(12)"));
        setting.click();
        driver.findElement(By.cssSelector("ul.docs li#doc-defaults")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-general")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-listings")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-images")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-checkout")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-advanced")).click();
        driver.findElement(By.cssSelector("ul.docs li#doc-security")).click();

        //SLIDES
        WebElement slide = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(13)"));
        slide.click();

        //TAX
        WebElement tax = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(14)"));
        tax.click();
        driver.findElement(By.cssSelector("ul.docs li#doc-tax_rates")).click();
        //TRANSLATION
        WebElement translation = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(15)"));
        translation.click();
        driver.findElement(By.cssSelector("ul.docs li#doc-scan")).click();

        //#doc-csv
    }
    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
           // wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
//    boolean areElementsPresent(WebDriver driver, By locator) {
//        return driver.findElements(locator).size() > 0;
//    }

    boolean doesItHaveTitle(WebElement element){
        return isElementPresent(driver, By.tagName("h1"));
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
