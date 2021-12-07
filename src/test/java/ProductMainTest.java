import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class ProductMainTest {

    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeClass
    public static void setUp() {
//      WebDriverManager.chromedriver().setup();
//      driver = new ChromeDriver();
//      WebDriverManager.iedriver().setup();
//      driver = new InternetExplorerDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/en/");
    }

    //в)-main
    @Test
    public void priceRegCrossedOutAndGrayOnMainPageTest() {
        WebElement regularPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[@class='regular-price']"));
        String fontRegPriceOnMainPage = regularPrice.getCssValue("text-decoration-line");
        String colorRegPriceOnMainPage = regularPrice.getCssValue("color");

        BaseTest testOnMainPagePrice = new BaseTest();

        Assert.assertTrue(testOnMainPagePrice.crossOut(fontRegPriceOnMainPage)
                && testOnMainPagePrice.colorIsGray(colorRegPriceOnMainPage));
    }

    //г)-main
    @Test
    public void priceCampBoldAndRedOnMainPageTest() {
        WebElement campaignPrice = driver.findElement(By.xpath("//div[@id= 'box-campaigns']//*[@class = 'campaign-price']"));
        String fontRegPriceOnMainPage = campaignPrice.getCssValue("font-weight");
        String colorRegPriceOnMainPage = campaignPrice.getCssValue("color");

        BaseTest testOnMainPagePrice = new BaseTest();

        Assert.assertTrue(testOnMainPagePrice.boldFont(fontRegPriceOnMainPage)
                && testOnMainPagePrice.colorIsRed(colorRegPriceOnMainPage));
    }

    //д)-main
    @Test
    public void priceCampHigherThanRegOnMainPageTest() {
        WebElement regularPrice = driver.findElement(By.xpath("//div[@id= 'box-campaigns']//*[@class = 'regular-price']"));
        WebElement campaignPrice = driver.findElement(By.xpath("//div[@id= 'box-campaigns']//*[@class = 'campaign-price']"));

        String sizeCompPriceOnMainPage = campaignPrice.getCssValue("font-size").replaceAll("[a-z]", "");
        String sizeRegPriceOnMainPage = regularPrice.getCssValue("font-size").replaceAll("[a-z]", "");
        double sizeComp = Double.parseDouble(sizeCompPriceOnMainPage);
        double sizeReg = Double.parseDouble(sizeRegPriceOnMainPage);

        Assert.assertTrue(sizeComp > sizeReg);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

