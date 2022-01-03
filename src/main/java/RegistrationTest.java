import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
public class RegistrationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String sampleText;
    private String phone;

    @Before
    public void start(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void findAllSmallDucks() throws InterruptedException {
        sampleText = "12345";
        phone = "12345";
        MailGeneration mail = new MailGeneration();
        String email = (String) mail.generateEmail();
        driver.get("http://localhost:8090/litecart/en/create_account");
        driver.findElement(By.name("tax_id")).sendKeys(sampleText);
        for(int i=0; i<8;i++) {
            new Actions(driver)
                    .sendKeys(Keys.TAB)
                    .sendKeys(sampleText)
                    .perform();
        }
        driver.findElement(By.cssSelector(".select2-selection.select2-selection--single")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("united states");
        new Actions(driver)
                .sendKeys(Keys.ENTER)
                .perform();
        Thread.sleep(2000);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("password")).sendKeys(sampleText);
        new Actions(driver)
                .sendKeys(Keys.TAB)
                .sendKeys(sampleText)
                .perform();
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.cssSelector("ul.list-vertical li:nth-child(4) a")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(sampleText);
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("ul.list-vertical li:nth-child(4) a")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }


}
