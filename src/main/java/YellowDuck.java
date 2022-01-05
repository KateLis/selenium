import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class YellowDuck {
    private WebDriver driver;
    private WebDriverWait wait;
    private String name;
    private String text;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void addNewProductsAndThenDeleteThem() throws InterruptedException, ProductNotAdded {
        driver.get("http://localhost:8090/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        //  driver.findElements(By.cssSelector("ul.listing-wrapper.products li")).get(0).click();
//            WebElement name = driver.findElement(By.cssSelector("[itemprop=name]"));
//            System.out.println(name.getText());
//            if (name.getText() == "Yellow Duck") {
//                driver.findElements(By.cssSelector(".options select option")).get(2).click();}
//                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
        for (int i = 1; i <= 3; i++) {
            driver.get("http://localhost:8090/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
            // driver.findElements(By.cssSelector("ul.listing-wrapper.products li")).get(0).click();
            WebElement name = driver.findElement(By.cssSelector("[itemprop=name]"));


            System.out.println(name.getText());
            System.out.println(driver.findElement(By.cssSelector("[itemprop=name]")).getText().equals("Yellow Duck"));
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
    }
}
