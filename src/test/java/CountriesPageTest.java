import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CountriesPageTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@type='checkbox']")).click();
        driver.findElement(By.xpath(".//button[@name='login']")).click();
    }

    @Test
    public void countriesInAlphabeticalOrderTest() {
        ArrayList<String> zones = new ArrayList<>();
        List<WebElement> zonesList = driver.findElements(By.xpath("//tr[@class='row'] //td[5]"));
        for (WebElement we : zonesList) {
            zones.add(we.getText());
        }
        ArrayList<String> sortedZonesList = new ArrayList<>();
        sortedZonesList.addAll(zones);
        Collections.sort(sortedZonesList);
        Assert.assertTrue(sortedZonesList.equals(zones));
    }

    @Test
    public void zoneGreaterThanZeroTest() {
        List<WebElement> zonesList = driver.findElements(By.xpath("//tr[@class='row']"));
        for (WebElement element : zonesList) {
            String zone = (element.findElement(By.xpath(".//td[5]//following::td[1]"))).getAttribute("innerHTML");

            if (Integer.parseInt(zone) != 0) {

                Actions act = new Actions(driver);
                act.keyDown(Keys.LEFT_CONTROL).keyDown(Keys.LEFT_SHIFT).perform();
                element.findElement(By.xpath(".//td[5]//a")).click();
                act.keyUp(Keys.LEFT_CONTROL).keyDown(Keys.LEFT_SHIFT).perform();

                ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(newTab.get(newTab.size() - 1));
                ArrayList<String> obtainedList = new ArrayList<>();

                List<WebElement> elementList = driver.findElements(By.xpath("//*[@id='table-zones']/tbody/tr[not(@class) and not(.//button[@name = 'add_zone'])]/td[3]"));

                for (WebElement zoneWeb : elementList) {
                    obtainedList.add(zoneWeb.getText());
                }
                ArrayList<String> sortedList = new ArrayList<>();
                for (String s : obtainedList) {
                    sortedList.add(s);
                }
                Collections.sort(sortedList);
                Assert.assertTrue(sortedList.equals(obtainedList));
                driver.close();
                driver.switchTo().window(newTab.get(0));
            }
        }
    }

    //14-задание
    @Test
    public void checkWhenEditingCountryLinksOpenInNewWindowTest(){
        driver.findElement(By.cssSelector("a[href*='code=AL'][ title = 'Edit']")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("form[method='post'] [target='_blank']"));
        for (WebElement link : links)
        {
            String current = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(current);
        }

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }


    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver d) {
                Set<String> windowHandles = d.getWindowHandles();
                windowHandles.removeAll(oldWindows);
                return windowHandles.size()>0 ? windowHandles.iterator().next() : null;
            }
        };
    }
}