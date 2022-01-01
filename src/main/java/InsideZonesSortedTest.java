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

public class InsideZonesSortedTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> preCountries;
    private List<WebElement> zonesInside;
    private List<String> zonesNames;
    private String abc;
    private Exception NotSortedException;
    private List<WebElement> countriesWithManyZones;
    private List<String> links;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void getCountriesNames() throws NotSortedException, InterruptedException {
        driver.get("http://localhost:8090/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost:8090/litecart/admin/?app=countries&doc=countries");
        preCountries = new ArrayList<>();
        countriesWithManyZones = new ArrayList<>();
        links = new ArrayList<>();
        preCountries = driver.findElements(By.cssSelector(".row"));
        zonesInside = new ArrayList<>();
        zonesNames = new ArrayList<>();
        System.out.println(preCountries.size());
        for (WebElement row : preCountries) {
            if (Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(6)")).getText()) > 0) {
                countriesWithManyZones.add(row);
            }
        }
        for (WebElement country : countriesWithManyZones) {
            links.add(country.findElement(By.cssSelector("a")).getAttribute("href"));
        }
        for (String link : links) {
            driver.get(link);
            zonesInside.addAll(driver.findElements(By.cssSelector(".header tr:nth-child(3)")));
            for (WebElement zone : zonesInside) {
                zonesNames.add(zone.getText());
            }
            List<String> sortedNames = zonesNames.stream()
                    .sorted()
                    .collect(Collectors.toList());
            if (zonesNames.equals(sortedNames)) {
                driver.get("http://localhost:8090/litecart/admin/?app=countries&doc=countries");
            }
            else throw new NotSortedException("Zones are not sorted");

        }


    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
