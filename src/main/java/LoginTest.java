import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void ExistingUserLogin(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        driver.manage().addCookie(new Cookie("test", "test"));
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
    }

    @Test
    public void NonExistingUserLogin(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("zakusova@eu.vi");
        driver.findElement(By.name("password")).sendKeys("1h2j3k");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void LongNameUser(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("WolfeschlegelsteinhausenbergerdorffWolfeschlegelsteinhausenbergerdorff");
    }
    @Test
    public void SpecialSymbolsInName(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("!@#$%^&*()_+:?></~`.");
    }

    @Test
    public void SpecialSymbolsInPassword(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("password")).sendKeys("!@#$%^&*()_+:?></~`.");
    }
    @Test
    public void NumbersInName(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("1234567890");
    }
    @Test
    public void NumbersInPassword(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("password")).sendKeys("1234567890");
    }
    @Test
    public void LinkIsClickable(){
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.cssSelector("img[src='/litecart/images/logotype.png']")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
