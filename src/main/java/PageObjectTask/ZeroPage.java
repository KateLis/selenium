package PageObjectTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZeroPage {
    final WebDriver driver;

    WebDriverWait wait;

    public ZeroPage(WebDriver driver,  WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


}
