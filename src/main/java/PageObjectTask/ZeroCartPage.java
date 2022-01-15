package PageObjectTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZeroCartPage extends ZeroPage{
    public ZeroCartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = ".dataTable.rounded-corners")
    public WebElement tableWithItems;

    public int getOnlyImportantRows() {
        WebElement rowOne = tableWithItems.findElement(By.cssSelector("tr"));
        wait.until( ExpectedConditions.visibilityOf(rowOne));
        int rowsWithProducts = tableWithItems.findElements(By.cssSelector("tr")).size() - 5;
        return rowsWithProducts;
    }

    public void checkThatAfterDeleteAllItemsWereDeleted() {
        for (int i = 1; i <= getOnlyImportantRows(); i++) {
            WebElement delete = driver.findElement(By.name("remove_cart_item"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(delete));
        }
    }
}
