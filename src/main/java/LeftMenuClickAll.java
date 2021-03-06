import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class LeftMenuClickAll {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void clickAndCheckLeftMenu() throws InterruptedException {
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        int count = 0;
        System.out.println(driver.findElements(By.cssSelector(".name")).size());
        for (int i = 0; i < driver.findElements(By.cssSelector(".name")).size(); i++) {
            // wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.cssSelector("#box-apps-menu li#app-")).get(i)));
            driver.findElements(By.cssSelector(".name")).get(i).click();
            if (driver.findElements(By.cssSelector(".docs")).size() > 0) {
                for (int j = 0; j < driver.findElements(By.cssSelector(".docs")).size(); j++) {
                    driver.findElements(By.cssSelector(".docs")).get(j).click();
                }
            }
            Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));
            Thread.sleep(2000);
            count++;
        }
        System.out.println(count);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
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
}
