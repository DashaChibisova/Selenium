package training.po.tests;

import org.junit.Assert;
import org.junit.Test;
import training.po.pages.CheckoutPage;
import training.po.pages.MainPage;
import training.po.pages.ProductPage;
import training.po.pages.TestBase;

public class WorkCartTest extends TestBase {
    @Test
    public void checkWorkCartTest() {
        MainPage mainPage  = new MainPage(driver);
        mainPage.open();

        for (int i = 0; i < 1; i++)
        {
            mainPage.chooseProduct().addNewProduct().returnOnMainPage();
        }

        CheckoutPage checkoutPage = mainPage.gotoCheckout().removeFromCart();
        Assert.assertTrue(checkoutPage.emptyCart());

    }
}
