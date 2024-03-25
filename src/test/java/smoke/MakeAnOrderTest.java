package smoke;

import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.baseObjects.BaseTest;

public class MakeAnOrderTest extends BaseTest {

    @Test(priority = 1)
    public void makeOrderTest() {
        get(HomePage.class)
                .open()
                .waitUntilPageLoaded()
                .acceptCookies()
                .cityChoise(0)
                .enterSearchItem("юбка");
        get(SearchResultPage.class)
                .waitUntilPageLoaded()
                .chooseProductCategory("Девочкам")
                .waitUntilPageLoaded()
                .goToItemPage(1);
        get(ProductPage.class)
                .waitUntilPageLoaded()
                .openCloseSizeTable()
                .chooseProductSize()
                .addProductToCart()
                .returnToProductPage()
                .goToMainPage();
        get(HomePage.class)
                .waitUntilPageLoaded()
                .enterSearchItem("брюки");
        get(SearchResultPage.class)
                .waitUntilPageLoaded()
                .chooseProductCategory("Мальчикам")
                .waitUntilPageLoaded()
                .goToItemPage(3);
        get(ProductPage.class)
                .waitUntilPageLoaded()
                .openCloseSizeTable()
                .chooseProductSize()
                .addProductToCart()
                .openBasket();
        get(ShoppingCartPage.class)
                .waitUntilPageLoaded()
                .addOneMoreItem(0)
                .waitUntilPageLoaded()
                .deleteItemFromCart(0)
                .waitUntilPageLoaded()
                .makeAnOrder();
    }
}
