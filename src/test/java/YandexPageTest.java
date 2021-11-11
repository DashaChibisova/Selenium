import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YandexPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\yandexdriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void YandexSearchTest() {

        driver.get("https://yandex.ru/");
        driver.findElement(By.id("text")).sendKeys("все получится!");
        driver.findElement(By.className("search2__button")).click();
        driver.findElement(By.className("UniSearchHeader-Title-Text")).click();

    }

    @After
    public void stop() {
        driver.quit();
    }
}
