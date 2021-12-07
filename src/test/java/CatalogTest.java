import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CatalogTest extends BaseTest{

    @Before
    public void setUp() {
        String url = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
        String className = "name";
        loginAdmin(url,className);
    }
    //17 задание
    @Test
    public void checkThatNotLogsInBrowserInProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("a:not([title = 'Edit'])[href*='product_id']"));
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productNames.add(products.get(i).getText());
        }
        for (int i = 0; i < products.size(); i++) {
            Actions act = new Actions(driver);
            act.keyDown(Keys.LEFT_CONTROL).keyDown(Keys.LEFT_SHIFT).perform();
            products.get(i).click();
            act.keyUp(Keys.LEFT_CONTROL).keyDown(Keys.LEFT_SHIFT).perform();

            ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(newTab.get(newTab.size() - 1));

            wait.until(ExpectedConditions.titleContains(productNames.get(i)));
            driver.manage().logs().get("browser").forEach(logs -> {
                System.out.println(logs);
                Assert.assertNull(logs);
            });
            driver.close();
            driver.switchTo().window(newTab.get(0));
            wait.until(ExpectedConditions.titleContains("Catalog"));
        }
    }

}
