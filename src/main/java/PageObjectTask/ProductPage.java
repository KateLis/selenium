package PageObjectTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends DriverPage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[itemprop=name]")
    private WebElement productName;

    public WebElement getProductName() {
        return productName;
    }

    public void addProductAndCheckIfCartItemIncreased(int i){
        String value = String.valueOf(Integer.parseInt(String.valueOf((i))));
        if (driver.findElement((By) productName).getText().equals("Yellow Duck")) {
            driver.findElements(By.cssSelector("option[value]")).get(2).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
            wait.until(ExpectedConditions.textToBe((By) productName, value)

            );
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"),
                    value));

        }
    }
}
