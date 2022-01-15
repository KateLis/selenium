package PageObjectTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ZeroMainPage extends ZeroPage{

    public ZeroMainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private final String url = "http://localhost:8090/litecart/en/";

    @FindBy(css = "ul.listing-wrapper.products li")
    public List<WebElement> customerRows;

    @FindBy(css = "span.quantity")
    public WebElement itemsAddedToCart;

    @FindBy(css = "#cart a.link")
    public WebElement checkoutCart;

    public void navigate() {
        driver.navigate().to(url);
    }

    public WebElement getCheckoutCart() {
        return checkoutCart;
    }

    public void clickOnFirstElement() {
        customerRows.get(0).click();
    }

    public void openMainPage() {
        driver.get("http://localhost:8090/litecart/en/");
    }


}
