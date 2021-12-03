import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void loginAdmin(String url, String className) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(url);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//button[@name='login']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public List<Integer> color(String color) {
        String[] colors = color.replaceAll("rgba", "")
                .split(","); //rgba(119, 119, 119, 1)
        List<Integer> result = new ArrayList<Integer>();
        for (String number : colors) {
            result.add(Integer.parseInt(number.replaceAll("[^0-9]", "")));
        }
        return result;
    }

    public boolean colorIsGray(String colorRGBA) {
        List<Integer> result = color(colorRGBA);
        return result.get(0) == result.get(1) && result.get(1) == result.get(2);
    }

    public boolean colorIsRed(String colorRGBA) {
        List<Integer> result = color(colorRGBA);
        return result.get(1) == 0 && result.get(2) == 0;
    }

    public boolean boldFont(String font) {
        return Integer.parseInt(font) >= 700 || font.equals("bold");
    }

    public boolean crossOut(String linethrough) {
        return linethrough.equals("line-through");
    }

}


