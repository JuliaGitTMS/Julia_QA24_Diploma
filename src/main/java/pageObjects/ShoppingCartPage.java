package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BaseLoadedPage;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ShoppingCartPage extends BaseLoadedPage<ShoppingCartPage> {
    private final By productAmount = By.className("ui-checkout-cart__products-count");
    private final By productList = By.className("_root_706i3_2");
    private final By itemQuantity = By.className("ui-incrementor__quantity");
    private final By itemCurrentPrice = By.xpath("//span[@class='_currentPrice_7h3td_87']/span");
    private final By itemDiscountPrice = By.xpath("//span[@class='_discountPrice_7h3td_91']/span");
    private final By increaseItemQuantity = By.className("ui-incrementor__increase");
    private final By decreaseItemQuantity = By.className("ui-incrementor__decrease");
    private final By deleteAllItems = By.className("_inner_13so9_2");
    private final By deleteItem = By.className("ui-checkout-cart__item-remove-from-cart");
    private final By totalPrice = By.xpath("(//div[@class='_value_1oots_21']/span)[2]");
    private final By checkout = By.className("ui-checkout__button-submit");

    public ShoppingCartPage checkProductsQuantity() {
        int sum = 0;
        for (int i = 0; i < driver.findElements(productList).size(); i++) {
            sum += getItemQuantity(i);
        }
        Assert.assertEquals(sum, parseInt(driver.findElement(productAmount).getText().substring(0, driver.findElement(productAmount).getText().indexOf(" "))));
        return me();
    }

    public int getItemQuantity(int index) {
        return parseInt(driver.findElement(itemQuantity).getText());
    }

    public Double getItemCost(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productList));
        if (driver.findElements(productList).get(index).findElement(itemCurrentPrice).isEnabled()) {
            return parseDouble(driver.findElements(productList).get(index).findElement(itemCurrentPrice).getText()
                    .substring(0,driver.findElements(productList).get(index).findElement(itemCurrentPrice).getText().indexOf(" ")));
        } else {
            return parseDouble(driver.findElements(productList).get(index).findElement(itemDiscountPrice).getText()
                    .substring(0, driver.findElements(productList).get(index).findElement(itemDiscountPrice).getText().indexOf(" ")));
        }
    }

    public ShoppingCartPage checkProductPrice(int index, Double price) {
        Assert.assertEquals(getItemCost(index), getItemQuantity(index) * price);
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
        Assert.assertEquals(parseDouble(driver.findElement(totalPrice).getText().substring(0, driver.findElement(totalPrice).getText().indexOf(" "))), sum);
        return me();
    }

    public ShoppingCartPage makeAnOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        click(checkout);
        return me();
    }

}
