package training.po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends Page{

    @FindBy(xpath ="//*[@name = 'options[Size]']")
    public List<WebElement> fieldSize;

    @FindBy(xpath ="//*[@id= 'cart']//span[@class = 'quantity']")
    public WebElement amountItemsInCart;

    @FindBy(xpath ="//*[@name= 'add_cart_product']")
    public WebElement buttonAddToCart;

    @FindBy(xpath ="//a[@href='/litecart/']")
    public WebElement homePage;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductPage addNewProduct(){
        if (fieldSize.size() >0) {
            fieldSize.get(0).click();
            new Select(fieldSize.get(0)).selectByIndex(1);
        }
        int cartSum = Integer.parseInt(amountItemsInCart.getText());
        buttonAddToCart.click();
        wait.until(ExpectedConditions.textToBePresentInElement(amountItemsInCart, String.valueOf(cartSum + 1)));
        return this;
    }

    public MainPage returnOnMainPage(){
        homePage.click();
        return new MainPage(driver);
    }
}
