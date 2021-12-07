package training.po.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends Page{

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//*[@class = 'image-wrapper shadow']")
    public List<WebElement> products;

    @FindBy(xpath ="//*[@class = 'dataTable rounded-corners']")
    @CacheLookup
    public WebElement tableOrderSummary;

    @FindBy(name ="remove_cart_item")
    public WebElement buttonRemove;

    @FindBy(css ="#checkout-cart-wrapper p")
    public WebElement emptyCart;

    public CheckoutPage removeFromCart() {
        for (int i = 0; i < products.size(); i++) {
            WebElement table = tableOrderSummary;
            buttonRemove.click();
            wait.until(ExpectedConditions.stalenessOf(table));

        }
        return this;
    }

    public boolean emptyCart()
    {
        return wait.until(ExpectedConditions
                    .textToBePresentInElement(emptyCart,"There are no items in your cart."));
    }

}
