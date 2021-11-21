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

public class AdminPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginAdminTest() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@type='checkbox']")).click();
        driver.findElement(By.xpath(".//button[@name='login']")).click();

    }

    @Test
    public void clickMenuItemsTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@type='checkbox']")).click();
        driver.findElement(By.xpath(".//button[@name='login']")).click();
        List<WebElement> items = driver.findElements(By.xpath("//li[contains(@id, 'app-')]"));
        List<WebElement> subitems;

        for (int i = 1; i < items.size() + 1; i++) {
            driver.findElement(By.xpath(".//li[contains(@id, 'app-')][" + i + "]")).click();
            boolean isEmpty = driver.findElements(By.xpath(".//h1")).isEmpty();
            Assert.assertFalse(isEmpty);
            subitems = driver.findElements(By.xpath("//li[contains(@id, 'doc')]"));
            for (int j = 1; j < subitems.size() + 1; j++) {
                driver.findElement(By.xpath(".//li[contains(@id, 'doc')][" + j + "]")).click();
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}

