import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ClickAllLeftMenuItems {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        // driver = new ChromeDriver();
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver,10);
    //    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
        Thread.sleep(4000);

//        WebElement template = driver.findElement(By.cssSelector("#doc-template"));
//        template.click();
//        doesItHaveTitle(template);
//        Thread.sleep(4000);
//
        WebElement logotype = driver.findElement(By.cssSelector("#doc-logotype")); //logotype
        logotype.click();
        doesItHaveTitle(logotype);
        Thread.sleep(4000);

//        WebElement catalog = driver.findElement(By.cssSelector("#app- li:nth-child(2)"));
//        catalog.click();
//        doesItHaveTitle(catalog);

        WebElement catalog1 = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(1)"));
        catalog1.click();
        Thread.sleep(4000);

        WebElement catalog2 = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2)"));
        catalog2.click();
        Thread.sleep(4000);
       // catalog2.findElement(By.cssSelector("#doc-product_groups")).click();

        WebElement countries = driver.findElement(By.cssSelector("ul#box-apps-menu li:nth-child(3)"));
        countries.click();
        Thread.sleep(4000);

        WebElement currencies = driver.findElement(By.cssSelector("ul#box-apps-menu li:nth-child(4)"));
        currencies.click();
        Thread.sleep(4000);

    }
    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            //driver.findElement(locator);
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    boolean doesItHaveTitle(WebElement element){
        return isElementPresent(driver, By.tagName("h1"));
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
