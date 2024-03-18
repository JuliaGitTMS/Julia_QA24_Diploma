package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BaseLoadedPage;

import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ShoppingCartPage extends BaseLoadedPage<ShoppingCartPage> {

    private final By price = By.xpath(".//div[contains(@class, '_wrapper')]/span");
    private final By productAmount = By.className("ui-checkout-cart__products-count");
    private final By productList = By.xpath("//div[contains(@class,'cart__item')]");
    private final By itemQuantity = By.xpath("//div[@aria-label='Изменить количество товара']/div");
    private final By increaseItemQuantity = By.xpath("//button[@aria-label='Увеличить']");
    private final By decreaseItemQuantity = By.xpath("//button[@aria-label='Уменьшить']");
    private final By deleteAllItems = By.className("_inner_13so9_2");
    private final By deleteItem = By.className("ui-checkout-cart__item-remove-from-cart");
    private final By totalPrice = By.xpath("(//div[@class='_value_1oots_21']/span)[2]");
    private final By checkout = By.className("ui-checkout__button-submit");
    private By getProduct(Integer productIndex) {
        return By.xpath("(//div[contains(@class,'cart__item')])[" + productIndex + "]");
    }

    public ShoppingCartPage checkProductsQuantity() {
        int sum = 0;
        for (int i = 0; i < driver.findElements(productList).size(); i++) {
            sum += getItemQuantity(i);
        }
        Assert.assertEquals(sum, parseInt(driver.findElement(productAmount).getText().substring(0, driver.findElement(productAmount).getText().indexOf(" "))));
        return me();
    }

    public int getItemQuantity(int index) {
        return parseInt(driver.findElements(productList).get(index).findElement(itemQuantity).getText());
    }

    public Double getItemCost(Integer productIndex) {
        List<WebElement> prices = driver.findElement(getProduct(productIndex + 1)).findElements(price);
        return parseDouble(prices.get(prices.size() == 1 ? 0 : 1).getText().split(" ")[0]);
    }

    public ShoppingCartPage checkProductPrice(int productIndex, Double price) {
        Assert.assertEquals(getItemCost(productIndex), price);
        return me();
    }

    public ShoppingCartPage addOneMoreItem(int index) {
        if (driver.findElements(productList).get(index).findElement(increaseItemQuantity).isEnabled()) {
            click(driver.findElements(productList).get(index).findElement(increaseItemQuantity));
        }
        return me();
    }

    public ShoppingCartPage deleteOneUnitOfItem(int index) {
        if (driver.findElements(productList).get(index).findElement(decreaseItemQuantity).isEnabled()) {
            click(driver.findElements(productList).get(index).findElement(decreaseItemQuantity));
        }
        return me();
    }

    public ShoppingCartPage deleteItemFromCart(int index) {
        click(driver.findElements(productList).get(index).findElement(deleteItem));
        return me();
    }

    public ShoppingCartPage deleteAllItemsFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAllItems));
        click(deleteAllItems);
        return me();
    }

    public ShoppingCartPage checkTotalPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
        Double sum = 0d;
        for (int i = 0; i < driver.findElements(productList).size(); i++) {
            sum = Double.sum(sum, getItemCost(i));
        }
        Assert.assertEquals(parseDouble(driver.findElement(totalPrice).getText().substring(0, driver.findElement(totalPrice).getText().indexOf("р")).replaceAll(" ", "")), sum);
        return me();
    }

    public ShoppingCartPage makeAnOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        click(checkout);
        return me();
    }

}
