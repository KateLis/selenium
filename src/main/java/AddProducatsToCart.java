import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AddProducatsToCart {
    private WebDriver driver;
    private WebDriverWait wait;
    private String name;
    private String text;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void addNewProductsAndThenDeleteThem() throws InterruptedException, ProductNotAdded {

        driver.get("http://localhost:8090/litecart/en/");
        driver.findElements(By.cssSelector("ul.listing-wrapper.products li")).get(0).click();
      //  Thread.sleep(3000);

       // Thread.sleep(3000);
       // WebElement cartItem = driver.findElement(By.cssSelector("span.quantity");
        for(int i=0; i<3; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"),
                    String.valueOf(Integer.parseInt(String.valueOf((i))))));

        }
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), "3"));
        System.out.println(driver.findElement(By.cssSelector("span.quantity")).getText());
    }

}




//public class AddProducatsToCartAndDelete {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private String name;
//    private String text;
//
//    @Before
//    public void start() {
//        driver = new EdgeDriver();
//        wait = new WebDriverWait(driver, 20);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//
//    }
//
//    @Test
//    public void addNewProductsAndThenDeleteThem() throws InterruptedException, ProductNotAdded {
//
//        for (int i = 1; i <= 3; i++) {
//            driver.get("http://localhost:8090/litecart/en/");
//            driver.findElements(By.cssSelector("ul.listing-wrapper.products li")).get(0).click();
//            if(driver.findElement(By.cssSelector(".options")).isDisplayed()){
//                driver.findElements(By.cssSelector(".options option")).get(2).click();
//            }
//            else {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
//                wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"),
//                        String.valueOf(Integer.parseInt(String.valueOf((i))))));
//
//            }
//            //      }
//            System.out.println(driver.findElement(By.cssSelector("span.quantity")).getText());
//            driver.findElement(By.cssSelector("#cart a.link")).click();
//
//            WebElement table = driver.findElement(By.cssSelector(".dataTable.rounded-corners"));
//            int rowsWithProducts = table.findElements(By.cssSelector("tr")).size() - 5;
//            for (int i = 1; i <= rowsWithProducts; i++) {
//                WebElement delete = driver.findElement(By.name("remove_cart_item"));
//
//                driver.findElement(By.name("remove_cart_item")).click();
//                wait.until(ExpectedConditions.stalenessOf(table));
//                wait.until(ExpectedConditions.stalenessOf(delete));
//                // Thread.sleep(3000);
//            }
//
//
//        }
//
//        boolean isElementPresent(WebDriver driver, By locator) {
//            try {
//                //driver.findElement(locator);
//                wait.until((WebDriver d) -> d.findElement(locator));
//                return true;
//            } catch (NoSuchElementException ex) {
//                return false;
//            }
//        }
//    }