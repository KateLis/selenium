import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void checkBrowserLogs() throws NotSortedException {
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2)")).click();
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        for(int i = 0; i< driver.findElements(By.cssSelector(".dataTable tr.row")).size(); i++) {
            driver.findElements(By.cssSelector(".dataTable tr.row")).get(i).findElement(By.cssSelector("a")).click();
            driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
            driver.manage().logs().get("client").forEach(l -> System.out.println(l));
            driver.manage().logs().get("driver").forEach(l -> System.out.println(l));
            driver.get("http://localhost:8090/litecart/admin/?app=catalog&doc=catalog&category_id=2");
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
