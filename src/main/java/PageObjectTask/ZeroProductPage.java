package PageObjectTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZeroProductPage extends ZeroPage{
       public ZeroProductPage(WebDriver driver, WebDriverWait wait) {
           super(driver, wait);;
    }

    @FindBy(css = "[itemprop=name]")
    public WebElement productName;

    public void addProductAndCheckIfCartItemIncreased(int i){
        String value = String.valueOf(Integer.parseInt(String.valueOf((i))));
        if (productName.getText().equals("Yellow Duck")) {
            driver.findElements(By.cssSelector("option[value]")).get(2).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product"))).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), value));
    }
}
