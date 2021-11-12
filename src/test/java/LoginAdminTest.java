import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginAdminTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\yandexdriver.exe");
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

    @After
    public void stop() {
        driver.quit();
    }
}
