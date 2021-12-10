package ru.stqa.cucumber.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.cucumber.pages.CheckoutPage;
import ru.stqa.cucumber.pages.MainPage;
import ru.stqa.cucumber.pages.TestBase;

public class WorkCartTest extends TestBase {
    @Test
    public void checkWorkCartTest() {
        MainPage mainPage = new MainPage(driver);
       // mainPage.open();

//        for (int i = 0; i < 1; i++) {
//            mainPage.chooseProduct().addNewProduct().returnOnMainPage();
//        }

        mainPage.addProductInCart(1);

        CheckoutPage checkoutPage = mainPage.gotoCheckout().removeFromCart();
        Assert.assertTrue(checkoutPage.emptyCart());
    }
}
