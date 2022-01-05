import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AddProducatsToCartAndDelete {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void addNewProductsAndThenDeleteThem() {

        for (int i = 1; i <= 3; i++) {
            driver.get("http://localhost:8090/litecart/en/");
            driver.findElements(By.cssSelector("ul.listing-wrapper.products li")).get(0).click();
            WebElement name = driver.findElement(By.cssSelector("[itemprop=name]"));
            System.out.println(name.getText());
            if (driver.findElement(By.cssSelector("[itemprop=name]")).getText().equals("Yellow Duck")) {
                driver.findElements(By.cssSelector("option[value]")).get(2).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
                wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"),
                        String.valueOf(Integer.parseInt(String.valueOf((i))))));
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
                wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"),
                        String.valueOf(Integer.parseInt(String.valueOf((i))))));

            }
        }
        System.out.println(driver.findElement(By.cssSelector("span.quantity")).getText());
        driver.findElement(By.cssSelector("#cart a.link")).click();

        WebElement table = driver.findElement(By.cssSelector(".dataTable.rounded-corners"));
        int rowsWithProducts = table.findElements(By.cssSelector("tr")).size() - 5;
        for (int i = 1; i <= rowsWithProducts; i++) {
            WebElement delete = driver.findElement(By.name("remove_cart_item"));

            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
            wait.until(ExpectedConditions.stalenessOf(delete));
        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}