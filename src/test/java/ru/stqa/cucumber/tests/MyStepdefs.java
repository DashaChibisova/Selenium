package ru.stqa.cucumber.tests;

import io.cucumber.java8.En;
import org.junit.Assert;
import ru.stqa.cucumber.pages.*;

public class MyStepdefs extends CucumberTestBase  implements En{

    MainPage mainPage;
    ProductPage productPage ;
    CheckoutPage checkoutPage;

    public MyStepdefs() {

        Given("User is on main page", () -> {
            mainPage= new MainPage(driver);
            mainPage.open();});
        When("User  select a product click on it and", () -> {
            mainPage.chooseProduct();});
        And("add it to the cart on the product page", () -> {
            productPage = new ProductPage(driver);
            productPage.addNewProduct();});
        And("returns to the main page", () -> {
            productPage.returnOnMainPage();});
        And("goes to Checkout", () -> {
            mainPage.gotoCheckout();});
        And("user removes all items from the shopping cart//действия", () -> {
            checkoutPage = new CheckoutPage(driver);
            checkoutPage.removeFromCart();});
        Then("Cart should be empty  a//проверки", () -> {
            Assert.assertTrue(checkoutPage.emptyCart());
        });
    }
}
