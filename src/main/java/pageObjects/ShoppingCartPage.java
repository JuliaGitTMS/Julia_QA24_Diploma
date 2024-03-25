package pageObjects;

import entities.Product;
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
    private final By productAmount = By.xpath("//span[contains (@class, '__products-count')]");
    private final By productList = By.xpath("//div[contains(@class,'cart__item')]");
    private final By itemQuantity = By.xpath(".//div[@aria-label='Изменить количество товара']/div");
    private final By increaseItemQuantity = By.xpath(".//button[@aria-label='Увеличить']");
    private final By decreaseItemQuantity = By.xpath(".//button[@aria-label='Уменьшить']");
    private final By deleteItem = By.xpath(".//button[contains(@class, 'item-remove-from-cart')]");
    private final By totalPrice = By.xpath("(//div[@class='_value_1oots_21']/span)[2]");
    private final By checkout = By.className("ui-checkout__button-submit");
    private final By lastItem = By.xpath("//span [@aria-label= 'Последний']");

    private By getProduct(int productIndex) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
        return By.xpath("(//div[contains(@class,'cart__item')])[" + productIndex + "]");
    }

    public ShoppingCartPage checkProductsQuantity() {
        int sum = 0;
        for (int i = 0; i < driver.findElements(productList).size(); i++) {
            sum += getItemQuantity(i);
        }
        Assert.assertEquals(parseInt(driver.findElement(productAmount).getText().split(" ")[0]), sum);
        return me();
    }

    public int getItemQuantity(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
        if (driver.findElement(getProduct(index + 1)).findElements(lastItem).size() != 0) {
            return 1;
        }
        return parseInt(driver.findElement(getProduct(index + 1)).findElement(itemQuantity).getText());
    }

    public Double getItemCost(int productIndex) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
        List<WebElement> prices = driver.findElement(getProduct(productIndex + 1)).findElements(price);
        return parseDouble(prices.get(prices.size() == 1 ? 0 : 1).getText().split("р")[0].replaceAll(" ", ""));
    }

    public ShoppingCartPage checkProductPrice(int productIndex, Double productPrice) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(price));
        Assert.assertEquals(getItemCost(productIndex), productPrice);
        return me();
    }

    public ShoppingCartPage addOneMoreItem(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
        if (driver.findElement(getProduct(index + 1)).findElements(lastItem).size() == 0) {
            click(driver.findElement(getProduct(index + 1)).findElement(increaseItemQuantity));
        }
        return me();
    }

    public ShoppingCartPage deleteOneUnitOfItem(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
        if (driver.findElement(getProduct(index + 1)).findElements(lastItem).size() == 0) {
            click(driver.findElement(getProduct(index + 1)).findElement(decreaseItemQuantity));
        }
        return me();
    }

    public ShoppingCartPage deleteItemFromCart(int index) {
        click(driver.findElement(getProduct(index + 1)).findElement(deleteItem));
        return me();
    }

    public ShoppingCartPage deleteItemFromCart(Product product) {
        click(driver.findElement(getProduct(product.getIndex() + 1)).findElement(deleteItem));
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
