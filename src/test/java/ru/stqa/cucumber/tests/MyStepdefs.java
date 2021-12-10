package ru.stqa.cucumber.tests;

import io.cucumber.java8.En;
import org.junit.Assert;
import ru.stqa.cucumber.pages.CheckoutPage;
import ru.stqa.cucumber.pages.CucumberTestBase;
import ru.stqa.cucumber.pages.MainPage;


public class MyStepdefs extends CucumberTestBase implements En {
    MainPage mainPage;
    CheckoutPage checkoutPage;

    public MyStepdefs() {
        Given("User is on main page", () -> {
           });
        When("User add the selected products to the cart '{int}' times", (Integer amount) -> {
            mainPage= new MainPage(driver);
            mainPage.addProductInCart(amount);
        });
        And("user removes all items from the shopping cart", () -> {
             checkoutPage = mainPage.gotoCheckout().removeFromCart();

        });
        Then("Cart should be empty", () -> {
            Assert.assertTrue(checkoutPage.emptyCart());
        });
    }
}
