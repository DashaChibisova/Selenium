import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoZonesTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void zonesInAlphabeticalOrderTest() {
        driver.get(" http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@type='checkbox']")).click();
        driver.findElement(By.xpath(".//button[@name='login']")).click();

        List<WebElement> zoneElements = driver.findElements(By.xpath("//tr[@class='row']//td[not(@style)]//a"));
        for (WebElement zone : zoneElements) {

            Actions act = new Actions(driver);
            act.keyDown(Keys.LEFT_CONTROL).keyDown(Keys.LEFT_SHIFT).perform();
            zone.click();
            act.keyUp(Keys.LEFT_CONTROL).keyDown(Keys.LEFT_SHIFT).perform();

            ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(newTab.get(newTab.size() - 1));
            ArrayList<String> zonesList = new ArrayList<>();
            System.out.println(driver.getTitle());

            List<WebElement> innerZones = driver.findElements(By.xpath("//*[@id='table-zones']//tr[not(@class)]//td[3]//*[@selected]"));

            for (WebElement innerZone : innerZones) {
                zonesList.add(innerZone.getText());
            }

            ArrayList<String> sortedList = new ArrayList<>();
            sortedList.addAll(zonesList);
            Collections.sort(sortedList);
            Assert.assertTrue(sortedList.equals(zonesList));
            driver.close();
            driver.switchTo().window(newTab.get(0));
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}
