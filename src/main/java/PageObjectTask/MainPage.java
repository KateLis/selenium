package PageObjectTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends DriverPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul.listing-wrapper.products li")
    private List<WebElement> customerRows;

    @FindBy(css = "span.quantity")
    private WebElement itemsAddedToCart;

    public WebElement getCheckoutCart() {
        return checkoutCart;
    }

    public void setCheckoutCart(WebElement checkoutCart) {
        this.checkoutCart = checkoutCart;
    }

    @FindBy(css = "#cart a.link")
    private WebElement checkoutCart;

    public void clickOnFirstElement(){
        driver.findElements((By) customerRows).get(0).click();
    }

    public void openMainPage(){
        driver.get("http://localhost:8090/litecart/en/");
    }



}
