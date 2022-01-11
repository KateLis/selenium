package PageObjectTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends DriverPage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".dataTable.rounded-corners")
    private WebElement tableWithItems;

//    public WebElement getTableWithItems() {
//        return tableWithItems;
//    }

    private int rowsWithProducts =
            tableWithItems.findElements(By.cssSelector("tr")).size() - 5;

    public int getRowsWithProducts() {
        return rowsWithProducts;
    }

    public void checkThatAfterDeleteAllItemsWereDeleted() {
        for (int i = 1; i <= rowsWithProducts; i++) {
            WebElement delete = driver.findElement(By.name("remove_cart_item"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(delete));
        }
    }
}
