import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabetTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> preCountries;
    private List<String> countries;
    private String abc;
    private Exception NotSortedException;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void getCountriesNames() throws NotSortedException {
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost:8090/litecart/admin/?app=countries&doc=countries");
        preCountries = driver.findElements(By.cssSelector(".row td:nth-child(5)"));
        countries = new ArrayList<>();

        for (WebElement element : preCountries) {
            abc = element.getText();
            countries.add(abc);
        }
        List<String> sortedCountries = countries.stream()
                .sorted()
                .collect(Collectors.toList());

        if (countries.equals(sortedCountries)) {
        } else {
            throw new NotSortedException("The list is not alphabetically sorted");
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
