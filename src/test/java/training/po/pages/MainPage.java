package training.po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {

    @FindBy(xpath = "//*[@class = 'image-wrapper'][1]")
    public WebElement chooseAnyProduct;

    @FindBy(xpath = "//*[@id= 'cart']//*[contains(text(), 'Checkout')]")
    public WebElement checkout;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart/");
        return this;
    }

    public ProductPage chooseProduct() {
        chooseAnyProduct.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@itemprop= 'name']")));
        return new ProductPage(driver);
    }

    public CheckoutPage gotoCheckout() {
        checkout.click();
        wait.until(ExpectedConditions.titleContains("Checkout"));
        return new CheckoutPage(driver);
    }
}
