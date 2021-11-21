import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    }

    @Test
    public void stickerIsPresentOnEachProductTest() {
        driver.get("http://localhost/litecart/");
        List<WebElement> stickers = driver.findElements(By.xpath("//li[contains(@class, 'product column shadow hover-light')]"));
        for (WebElement sticker : stickers) {
            List<WebElement> web = sticker.findElements(By.xpath(".//div[contains(@class, 'sticker')]"));
            Assert.assertEquals(web.size(), 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
    }

}
