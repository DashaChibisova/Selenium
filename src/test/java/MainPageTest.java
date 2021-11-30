import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class MainPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void stickerIsPresentOnEachProductTest() {
        List<WebElement> stickers = driver.findElements(By.xpath("//li[contains(@class, 'product column shadow hover-light')]"));
        for (WebElement sticker : stickers) {
            List<WebElement> web = sticker.findElements(By.xpath(".//div[contains(@class, 'sticker')]"));
            Assert.assertEquals(web.size(), 1);
        }
    }

    //13 задание
    @Test
    public void checkWorkCartTest() {

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.xpath("//*[@class = 'image-wrapper'][1]")).click();

            if (driver.findElements(By.xpath("//*[@name = 'options[Size]']")).size() > 0) {
                driver.findElement(By.xpath("//*[@name = 'options[Size]']")).click();
                driver.findElement(By.xpath("//*[@name = 'options[Size]']/*[@value = 'Small']")).click();
            }
            WebElement cart = driver.findElement(By.xpath("//*[@id= 'cart']//span[@class = 'quantity']"));
            int cartSum = Integer.parseInt(cart.getText());
            driver.findElement(By.xpath("//*[@name= 'add_cart_product']")).click();
            wait.until(ExpectedConditions.textToBePresentInElement(cart, String.valueOf(cartSum + 1)));
            driver.findElement(By.xpath("//a[@href='/litecart/']")).click();
        }
        driver.findElement(By.xpath("//*[@id= 'cart']//*[contains(text(), 'Checkout')]")).click();

        int countProducts = driver.findElements(By.xpath("//*[@class = 'shortcuts']/*[@class = 'shortcut']")).size();
        for (int i = 0; i < countProducts; i++) {
            WebElement table = driver.findElement(By.cssSelector(".dataTable"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("em"))).getText().equals("There are no items in your cart."));
    }


    @After
    public void stop() {
        driver.quit();
    }
}
