import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void NonExistingUserLogin(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http:/localhost:8080/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("zakusova@eu.vi");
        driver.findElement(By.name("password")).sendKeys("1h2j3k");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }
    @Test
    public void LostPassword() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http:/localhost:8080/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("homecredit@gmail.com");
        driver.findElement(By.name("lost_password")).click();
    }

    @Test
    public void NewCustomersLink(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http:/localhost:8080/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
