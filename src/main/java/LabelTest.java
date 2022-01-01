import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LabelTest{
    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> ducks;
    private List<WebElement> st;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void findAllSmallDucks() throws NoStickerOnDuckException {
        driver.get("http:/localhost:8080/litecart/en/");
        ducks = driver.findElements(By.cssSelector(".product"));
        System.out.println("There are " + ducks.size() + " ducks");

        for (WebElement element : ducks){
            element.findElement(By.className("image"));
            st = element.findElements(By.className("sticker"));
            if(st.size()==1){
              System.out.println("Every duck has only one sticker");
            }
            else{
                throw new NoStickerOnDuckException("No sticker");
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }


}
