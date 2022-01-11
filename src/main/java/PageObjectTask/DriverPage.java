package PageObjectTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public DriverPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
}