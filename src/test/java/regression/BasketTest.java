package regression;

import entities.Product;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultPage;
import pageObjects.ShoppingCartPage;
import pageObjects.baseObjects.BaseTest;


public class BasketTest extends BaseTest {

    @Test(priority = 1)
    public void acceptCookies() {
        get(HomePage.class)
                .open()
                .waitUntilPageLoaded()
                .acceptCookies();
    }

    @Test(priority = 2, dataProvider = "product")
    public void addProductsTest(Product product) {
        get(HomePage.class)
                .waitUntilPageLoaded()
                .enterSearchItem(product.getName());
        get(SearchResultPage.class)
                .waitUntilPageLoaded()
                .chooseProductCategory(product.getCategory())
                .waitUntilPageLoaded()
                .goToItemPage(product.getIndex());
        get(ProductPage.class)
                .waitUntilPageLoaded();
        Double price = get(ProductPage.class).getProductPrice();
        get(ProductPage.class)
                .chooseProductSize()
                .addProductToCart()
                .openBasket();
        get(ShoppingCartPage.class)
                .waitUntilPageLoaded()
                .checkProductPrice(product.getIndex(), price)
                .goToWomenCategoryPage();
    }

    @Test(priority = 3)
    public void shoppingCartPriceTest() {
        get(HomePage.class)
                .waitUntilPageLoaded()
                .goToShoppingCart();
        get(ShoppingCartPage.class)
                .waitUntilPageLoaded()
                .checkTotalPrice()
                .addOneMoreItem(0)
                .waitUntilPageLoaded()
                .addOneMoreItem(1)
                .waitUntilPageLoaded()
                .checkTotalPrice()
                .deleteOneUnitOfItem(0)
                .waitUntilPageLoaded()
                .deleteOneUnitOfItem(1)
                .waitUntilPageLoaded()
                .checkTotalPrice();
    }

    @Test(priority = 4)
    public void shoppingCartQuantityTest() {
        Product product= new Product();
        product.setIndex(0);
        get(ShoppingCartPage.class)
                .waitUntilPageLoaded()
                .checkProductsQuantity()
                .addOneMoreItem(0)
                .waitUntilPageLoaded()
                .addOneMoreItem(1)
                .waitUntilPageLoaded()
                .checkProductsQuantity()
                .deleteOneUnitOfItem(0)
                .waitUntilPageLoaded()
                .deleteOneUnitOfItem(1)
                .checkProductsQuantity()
                .waitUntilPageLoaded()
                .deleteItemFromCart(product)
                .checkProductsQuantity();
    }

    @DataProvider(name = "product")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setName("брюки");
                    setCategory("Женщинам");
                    setIndex(0);
                }}},
                {new Product() {{
                    setName("шорты");
                    setCategory("Девочкам");
                    setIndex(1);
                }}},
                {new Product() {{
                    setName("юбка");
                    setCategory("Женщинам");
                    setIndex(2);
                }}},
        };
    }
}


