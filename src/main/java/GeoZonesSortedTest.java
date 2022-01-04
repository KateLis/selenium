import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GeoZonesSortedTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> preCountries;

    private List<String> zonesNames;
    private List<WebElement> countriesWithManyZones;
    private List<String> links;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void getCountriesNames() throws NotSortedException {
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost:8090/litecart/admin/?app=geo_zones&doc=geo_zones");
        preCountries = new ArrayList<>();
        countriesWithManyZones = new ArrayList<>();
        links = new ArrayList<>();
        preCountries = driver.findElements(By.cssSelector(".row"));
        zonesNames = new ArrayList<>();

        for (WebElement country : preCountries) {
            links.add(country.findElement(By.cssSelector("a")).getAttribute("href"));
        }
        for (String link : links) {
            driver.get(link);
    //нужно искать заново все эл-ты, чтобы не было StaleException
            for (int i = 0; i < (driver.findElements(By.cssSelector("#table-zones td:nth-child(3) select option[selected=selected]"))).size(); i++) {
                zonesNames.add((driver.findElements(By.cssSelector("#table-zones td:nth-child(3) select option[selected=selected]"))).get(i).getText());
            }
            System.out.println(zonesNames.size());

            List<String> sortedNames = zonesNames.stream()
                    .sorted()
                    .collect(Collectors.toList());
            if (zonesNames.equals(sortedNames)) {
                driver.get("http://localhost:8090/litecart/admin/?app=countries&doc=countries");
            } else throw new NotSortedException("Zones are not sorted");


        }

    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
