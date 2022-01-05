import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class NewProductAdmin {
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
    public void getCountriesNames() throws InterruptedException, ProductNotAdded {
        name = "Japan dog";
        text = "This is a japanese cat that can be your best friend";
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost:8090/litecart/admin/?app=catalog&doc=catalog");
        int rowSize = driver.findElements(By.cssSelector(".row")).size();
        System.out.println(rowSize);
        driver.findElement(By.cssSelector("#content div:nth-child(2) a:nth-child(2)")).click();
        driver.findElement(By.name("status")).click();
        driver.findElement(By.name("name[en]")).sendKeys(name);
        driver.findElement(By.name("code")).sendKeys(name);
        driver.findElements(By.name("categories[]")).get(2).click(); //
        driver.findElements(By.name("product_groups[]")).get(2).click();
        // driver.findElement(By.cssSelector("input[type=number]")).clear();
        driver.findElement(By.cssSelector("input[type=number]")).sendKeys("2");
        File file = new File("japan.jpg");
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys(absolutePath);
//       чтобы кликнуть по эл-ту, надо сдвинуть мышку. Но для загрузки файла кликать не надо
//        new Actions(driver)
//                .moveToElement(driver.findElement(By.cssSelector("input[type=file]")), 4, 4)
//                .click()
//                .perform();
        driver.findElements(By.cssSelector("input[type=date]")).get(0).clear();
        driver.findElements(By.cssSelector("input[type=date]")).get(0).sendKeys("01012022");
        driver.findElements(By.cssSelector("input[type=date]")).get(1).clear();
        driver.findElements(By.cssSelector("input[type=date]")).get(1).sendKeys("01012023");
        driver.findElement(By.cssSelector("ul.index li:nth-child(2)")).click();
        driver.findElement(By.name("manufacturer_id")).click();
        WebElement corp2 = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("#tab-information select option:nth-child(2)")));
        corp2.click();
        driver.findElement(By.name("keywords")).sendKeys(name);
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(text);
        driver.findElement(By.name("head_title[en]")).sendKeys(name);
        driver.findElement(By.name("meta_description[en]")).sendKeys(name);

        driver.findElement(By.cssSelector("ul.index li:nth-child(4)")).click();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("500");
        driver.findElement(By.name("purchase_price_currency_code")).click();
        WebElement currency = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("#tab-prices select option:nth-child(2)")));
        currency.click();
        driver.findElement(By.name("prices[USD]")).sendKeys("1000");
        driver.findElement(By.name("prices[EUR]")).sendKeys("900");

        driver.findElement(By.name("save")).click();

        driver.get("http://localhost:8090/litecart/admin/?app=catalog&doc=catalog");
        int rowSizeAddNewProduct = driver.findElements(By.cssSelector(".row")).size();
        System.out.println(rowSizeAddNewProduct);
        if(rowSizeAddNewProduct == rowSize+1){
            System.out.println("It's added");
        }
        else {
            throw new ProductNotAdded("The product hasn't been saved");
        }


        Thread.sleep(3000);
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}