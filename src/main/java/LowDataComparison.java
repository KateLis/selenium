import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LowDataComparison {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void start() {

        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testNames() {
        driver.get("http://localhost:8090/litecart/en/");
        WebElement productName = driver.findElement(By.cssSelector("#box-campaigns .name"));
        WebElement regularPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        WebElement campaignPrice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));

        String productNameText = productName.getText();
        String regularPriceText = regularPrice.getText();
        String campaignPriceText = campaignPrice.getText();
        String regularPriceColor1 =  regularPrice.getCssValue("color");
        String campaignPriceColor1 = campaignPrice.getCssValue("color");
        String boldAttribute1 = campaignPrice.getAttribute("localName");
//        Dimension campaignPriceSize = campaignPrice.getSize();
//        Dimension regularPriceSize = regularPrice.getSize();
//        Assert.assertTrue(getElementSquare(campaignPriceSize) > getElementSquare(regularPriceSize));
        float campaignSize1 = getFontSize(campaignPrice);
        float regularSize1 = getFontSize(regularPrice);
        Assert.assertTrue(campaignSize1 > regularSize1);
        checkLineThrough(regularPrice);

        driver.findElement(By.cssSelector("#box-campaigns a")).click();

        WebElement productNameOnProductPage = driver.findElement(By.cssSelector("[itemprop=name]"));
        WebElement regularPriceOnProductPage = driver.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPriceOnProductPage = driver.findElement(By.cssSelector(".campaign-price"));

        String productNameOnProductPageText = productNameOnProductPage.getText();
        String regularPriceOnProductPageText = regularPriceOnProductPage.getText();
        String campaignPriceOnProductPageText = campaignPriceOnProductPage.getText();

        float campaignSize2 = getFontSize(campaignPriceOnProductPage);
        float regularSize2 = getFontSize(regularPriceOnProductPage);
        Assert.assertTrue(campaignSize2 > regularSize2);
        checkLineThrough(regularPriceOnProductPage);

//        Dimension campaignPriceSizeOnProductPage = campaignPriceOnProductPage.getSize();
//        Dimension regularPriceSizeOnProductPage = regularPriceOnProductPage.getSize();

//        Assert.assertTrue(
//                getElementSquare(campaignPriceSizeOnProductPage)
//                        > getElementSquare(regularPriceSizeOnProductPage));

        Assert.assertTrue(productNameText.equals(productNameOnProductPageText));
        Assert.assertTrue(regularPriceText.equals(regularPriceOnProductPageText));
        Assert.assertTrue(campaignPriceText.equals(campaignPriceOnProductPageText));

        String regularPriceColor2 = regularPriceOnProductPage.getCssValue("color");
        String campaignPriceColor2 = campaignPriceOnProductPage.getCssValue("color");
        String boldAttribute2 = campaignPriceOnProductPage.getAttribute("localName");

        checkColorIsGrey(regularPriceColor1);
        checkColorIsGrey(regularPriceColor2);
        checkColorIsRed(campaignPriceColor1);
        checkColorIsRed(campaignPriceColor2);
        Assert.assertTrue(boldAttribute1.equals("strong"));
        Assert.assertTrue(boldAttribute2.equals("strong"));

    }
    public void checkColorIsGrey(String elementColor) {
        String[] colorArr1 = elementColor.split(", ");
        String[] rBeforeSplit = colorArr1[0].split("\\(");

        int r = Integer.parseInt(rBeforeSplit[1]);
        int g = Integer.parseInt(colorArr1[1]);
        int b = Integer.parseInt(colorArr1[1]);
        Assert.assertTrue(r == g && g == b);

    }
    public void checkColorIsRed (String elementColor) {
        String[] colorArr1 = elementColor.split(", ");
        int g = Integer.parseInt(colorArr1[1]);
        int b = Integer.parseInt(colorArr1[1]);
        Assert.assertTrue(g == 0 && b ==0);
    }
    public int getElementSquare(Dimension dimension){
        int a1 = dimension.getHeight();
        int b1 = dimension.getWidth();
        return a1*b1;
    }
    public float getFontSize(WebElement element){
        String font = element.getCssValue("font-size");
        String[] fontArr = font.split("px");
        for(String str : fontArr){
            System.out.println(str);
        }
        return Float.parseFloat(fontArr[0]);
    }
    public void checkLineThrough(WebElement element){
        String ifLined = element.getCssValue("text-decoration");
        System.out.println(ifLined);
       Assert.assertTrue(ifLined.contains("line-through"));
    }
}

