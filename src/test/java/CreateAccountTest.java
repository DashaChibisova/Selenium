import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class CreateAccountTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//      WebDriverManager.iedriver().setup();
//      driver = new InternetExplorerDriver();
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/en/");
    }


    //д)-main
    @Test
    public void createNewAccountTest() {
        driver.findElement(By.xpath("//*[@id = 'box-account-login']" +
                "//a[@href='http://localhost/litecart/en/create_account']"))
                .click();
        DataNewAccount newAccount = new DataNewAccount("dasha", "Dasha", "Shilo",
                "11111", "Raif", "Chib", "Address2", "Moscow",
                "89552228635", "123456");


        driver.findElement(By.name("tax_id")).sendKeys(newAccount.getTaxID());
        driver.findElement(By.name("firstname")).sendKeys(newAccount.getFirstName());
        driver.findElement(By.name("address1")).sendKeys(newAccount.getAddress1());
        driver.findElement(By.name("postcode")).sendKeys(newAccount.getPostcode());
        driver.findElement(By.name("company")).sendKeys(newAccount.getCompany());
        driver.findElement(By.name("lastname")).sendKeys(newAccount.getLastName());
        driver.findElement(By.name("address2")).sendKeys(newAccount.getAddress2());
        driver.findElement(By.name("city")).sendKeys(newAccount.getCity());
        driver.findElement(By.name("email")).sendKeys(newAccount.getEmail());
        driver.findElement(By.name("phone")).sendKeys(newAccount.getPhone());
        driver.findElement(By.name("password")).sendKeys(newAccount.getDesiredPassword());
        driver.findElement(By.name("confirmed_password")).sendKeys(newAccount.getDesiredPassword());
        driver.findElement(By.xpath("//*[@class= 'select2-selection select2-selection--single']")).click();

        List<WebElement> countryList = driver.findElements(By.xpath("//li[@class = 'select2-results__option']"));

        for (WebElement country : countryList)
            if ("United States".equals(country.getText())) {
                country.click();
                return;
            }
        driver.findElement(By.xpath("//button[contains(text(),'Create Account')]")).click();
        driver.findElement(By.xpath("//*[@id=\"footer\"]//a[@href='http://localhost/litecart/en/logout']")).click();

        driver.findElement(By.xpath("//*[@name = 'login_form']//*[@name = 'email']"))
                .sendKeys(newAccount.getEmail());
        driver.findElement(By.xpath("//*[@name = 'login_form']//*[@name = 'password']"))
                .sendKeys(newAccount.getDesiredPassword());
           driver.findElement(By.xpath("//*[@id=\"footer\"]//a[@href='http://localhost/litecart/en/logout']")).click();

    }


    @After
    public  void tearDown() {
        driver.quit();
    }
}
