import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriesTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    BaseTest testOnMainPagePrice = new BaseTest();


    @BeforeClass
    public static void setUp() {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
//      WebDriverManager.iedriver().setup();
//      driver = new InternetExplorerDriver();
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/en/");


    }
    //a,б)
    @Test
    public void aclickingOnProductOpensTheCorrectPageTest() {

        String nameOnMainPage = driver.findElement(By.xpath("//*[@id = 'box-campaigns']//*[@class = 'name']")).getText();
        String regularPriceOnMainPage = driver.findElement(By.xpath("//*[@id = 'box-campaigns']//*[@class = 'regular-price']")).getText();
        String campaignPriceOnMainPage = driver.findElement(By.xpath("//*[@id = 'box-campaigns']//*[@class = 'campaign-price']")).getText();

        driver.findElement(By.xpath("//div[@id= 'box-campaigns']//li[@class = 'product column shadow hover-light']")).click();

        String nameOnProductPage = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'title']")).getText();
        String regularPriceOnProductPage = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'regular-price']")).getText();
        String campaignPriceOnProductPage = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'campaign-price']")).getText();

        Assert.assertEquals(nameOnMainPage, nameOnProductPage);
        Assert.assertEquals(regularPriceOnMainPage, regularPriceOnProductPage);
        Assert.assertEquals(campaignPriceOnMainPage, campaignPriceOnProductPage);
    }

    //в)
    @Test
    public void bpriceRegCrossedOutAndGrayOnCategoriesPageTest() {
        WebElement regularPrice = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'regular-price']"));
        String fontRegPriceOnMainPage = regularPrice.getCssValue("text-decoration-line");
        String colorRegPriceOnMainPage = regularPrice.getCssValue("color");

        Assert.assertTrue(testOnMainPagePrice.crossOut(fontRegPriceOnMainPage)
                && testOnMainPagePrice.colorIsGray(colorRegPriceOnMainPage));

    }
    //г)
    @Test
    public void cpriceCampBoldAndRedOnCategoriesPageTest() {
        WebElement campaignPrice = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'campaign-price']"));
        String fontRegPriceOnMainPage = campaignPrice.getCssValue("font-weight"); //жирная
        String colorRegPriceOnMainPage = campaignPrice.getCssValue("color"); //rgba(119, 119, 119, 1)

        Assert.assertTrue(testOnMainPagePrice.boldFont(fontRegPriceOnMainPage)
                && testOnMainPagePrice.colorIsRed(colorRegPriceOnMainPage));

    }
    //д)
    @Test
    public void dpriceCampHigherThanRegOnCategoriesPageTest() {
        WebElement campaignPrice = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'campaign-price']"));
        WebElement regularPrice = driver.findElement(By.xpath("//div[@id= 'box-product']//*[@class = 'regular-price']"));

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

