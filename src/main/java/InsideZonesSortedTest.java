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

    List<String> zonesNames;
    private List<WebElement> countriesWithManyZones;
    private List<String> links;
    List<WebElement> allRows;
    List<WebElement> allNamesRows;

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
        driver.get("http://localhost:8090/litecart/admin/?app=countries&doc=countries");
        preCountries = new ArrayList<>();
        countriesWithManyZones = new ArrayList<>();
        links = new ArrayList<>();
        preCountries = driver.findElements(By.cssSelector(".row"));;


        for (WebElement row : preCountries) {
            if (Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(6)")).getText()) > 0) {
                countriesWithManyZones.add(row);
            }
        }
        for (WebElement country : countriesWithManyZones) {
            links.add(country.findElement(By.cssSelector("a")).getAttribute("href"));
        }
        for (String link : links) {
            allRows = new ArrayList<>();
            zonesNames = new ArrayList<>();
            allNamesRows = new ArrayList<>();
            driver.get(link);
            WebElement emptyRow = driver.findElement(By.cssSelector("#table-zones tr:last-child"));
            WebElement header = driver.findElement(By.cssSelector("#table-zones tr:first-child"));
            allRows = driver.findElements(By.cssSelector("#table-zones tr"));

            System.out.println(allRows.remove(emptyRow));
            System.out.println(allRows.remove(header));

            System.out.println("All rows: " + allRows.size());

            for (int i = 0; i < allRows.size(); i++) {
                allNamesRows.add(allRows.get(i).findElement(By.cssSelector("td:nth-child(3)")));
                zonesNames.add(allNamesRows.get(i).getText());
            }
            System.out.println("All names: " + zonesNames.size());
            System.out.println(zonesNames);

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
