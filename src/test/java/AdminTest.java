import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AdminTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public String string = RandomStringUtils.randomAlphabetic(10);

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
    //12 -задание
    @Test
    public void addProductAdminTest() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath(".//input[@type='checkbox']")).click();
        driver.findElement(By.xpath(".//button[@name='login']")).click();
        driver.findElement((By.xpath("//*[@id = 'box-apps-menu']//*[contains(text(), 'Catalog')]"))).click();
        driver.findElement((By.xpath("//*[@id='content']//a[@href = 'http://localhost/litecart/admin" +
                "/?category_id=0&app=catalog&doc=edit_product']"))).click();

        WebElement generalTab = driver.findElement(By.xpath("//*[@id='tab-general']"));
        generalTab.findElement(By.xpath(".//label[1]")).click();
        generalTab.findElement(By.xpath(".//tr[2]//*[@type = 'text']")).sendKeys(string);
        generalTab.findElement(By.xpath(".//tr[3]//*[@type = 'text']")).sendKeys("03");
        generalTab.findElement(By.xpath(".//tr[5]//*[@name = 'default_category_id']")).click();
        generalTab.findElement(By.xpath(".//tr[5]//*[@value= '0']")).click();
        generalTab.findElement(By.xpath(".//tr[7]//*[@value = '1-3']")).click();
        generalTab.findElement(By.xpath(".//tr[8]//*[@name = 'quantity']")).click();
        generalTab.findElement(By.xpath(".//tr[8]//*[@name = 'quantity']")).clear();
        generalTab.findElement(By.xpath(".//tr[8]//*[@name = 'quantity']")).sendKeys("3");

        driver.findElement(By.xpath("//*[@name = 'quantity_unit_id']")).click();
        driver.findElement(By.xpath("//*[@name = 'quantity_unit_id']//*[@value = '1']")).click();
        driver.findElement(By.xpath("//*[@name = 'delivery_status_id']")).click();
        driver.findElement(By.xpath("//*[@name = 'delivery_status_id']//*[@value = '1']")).click();
        driver.findElement(By.xpath("//*[@name = 'sold_out_status_id']")).click();
        driver.findElement(By.xpath("//*[@name = 'sold_out_status_id']//*[@value = '2']")).click();
        driver.findElement(By.xpath("//*[@name = 'date_valid_from']")).click();
        driver.findElement(By.xpath("//*[@name = 'date_valid_from']")).sendKeys(Keys.HOME + "20102021");
        driver.findElement(By.xpath("//*[@name = 'date_valid_to']"));

        String str = "src\\DuckQuack.jpg";
        String path = Path.of(str).toAbsolutePath().toString();

        driver.findElement(By.xpath("//*[@name = 'new_images[]']")).sendKeys(path);
        driver.findElement(By.xpath("//a[contains(@href, '#tab-information')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//*[@id = 'tab-information']//*[contains(text(), 'Manufacturer')]"))));
        driver.findElement(By.xpath("//*[@name= 'manufacturer_id']")).click();
        driver.findElement(By.xpath("//*[@name= 'manufacturer_id']//*[@value = '1']")).click();
        driver.findElement(By.xpath("//*[@name= 'supplier_id']")).click();
        driver.findElement(By.xpath("//*[@name= 'supplier_id']//*[@selected]")).click();
        driver.findElement(By.xpath("//*[@name = 'keywords']")).click();
        driver.findElement(By.xpath("//*[@name = 'keywords']")).sendKeys(string);
        driver.findElement(By.xpath("//*[@name = 'short_description[en]']")).click();
        driver.findElement(By.xpath("//*[@name = 'short_description[en]']")).sendKeys(string);
        driver.findElement(By.xpath("//*[@class= 'trumbowyg-editor']")).click();
        driver.findElement(By.xpath("//*[@class= 'trumbowyg-editor']")).sendKeys(string);
        driver.findElement(By.xpath("//*[@name= 'head_title[en]']")).click();
        driver.findElement(By.xpath("//*[@name= 'head_title[en]']")).sendKeys(string);
        driver.findElement(By.xpath("//*[@name= 'meta_description[en]']")).click();
        driver.findElement(By.xpath("//*[@name= 'meta_description[en]']")).sendKeys(string);
        driver.findElement(By.xpath("//a[contains(@href, '#tab-prices')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//*[@id = 'tab-prices']//*[contains(text(), 'Prices')]"))));
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("5");
        driver.findElement(By.name("purchase_price_currency_code")).click();
        driver.findElement(By.xpath("//*[@name = 'purchase_price_currency_code']/*[@data-value= '1']")).click();
        driver.findElement(By.xpath("//*[@name='prices[USD]']")).click();
        driver.findElement(By.xpath("//*[@name='prices[USD]']")).sendKeys("5");
        driver.findElement(By.xpath("//*[@name='prices[EUR]']")).click();
        driver.findElement(By.xpath("//*[@name='prices[EUR]']")).sendKeys("5.5");
        driver.findElement(By.xpath("//*[@name='save']")).click();

        List<WebElement> products = driver.findElements(By.xpath("//*[@id='content']//tr[*]"));
        List<String> product = new ArrayList<>();

        for (int i = 2; i < products.size(); i++) {
            product.add(driver.findElement(By.xpath("//*[@id='content']//tr[" + i + "]//a")).getText());
        }
        if (product.contains(string)) {
        } else {
            throw new NoSuchElementException("Продукт не найден");
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}
