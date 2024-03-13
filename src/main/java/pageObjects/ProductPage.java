package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.baseObjects.BaseLoadedPage;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ProductPage extends BaseLoadedPage<ProductPage> {
    private final By price = By.xpath("//span[@aria-label='Итоговая цена']");
    private final By sizeBar = By.className("_select_1widv_10");
    private final By sizeTableOpen = By.className("_sizetableButton_1widv_225");
    private final By sizeTableClose = By.className("_close_1ezsj_40");
    private final By sizeList = By.className("_colspanMain_1widv_182");
    private final By addToBasket = By.xpath("//button[@aria-label='Добавить в корзину']");
    private final By returnToProductPage = By.xpath("//div[@class='d-modal__bottom']/button");
    private final By goToBasket = By.xpath("//div[@class='d-modal__bottom']/a");
    private final By goToMainPage = By.xpath("//a[@title='Главная']");

    public ProductPage openCloseSizeTable() {
        click(sizeTableOpen);
        wait.until(ExpectedConditions.elementToBeClickable(sizeTableClose));
        click(sizeTableClose);
        return me();
    }

    public Double getProductPrice() {
        return parseDouble(driver.findElement(price).getText().substring(0, driver.findElement(price).getText().indexOf(" ")));
    }

    public ProductPage chooseProductSize() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sizeBar));
        click(sizeBar);
        for (int i = 0; i <= driver.findElements(sizeList).size(); i++) {
            if (driver.findElements(sizeList).get(i).isEnabled()) {
                click(driver.findElements(sizeList).get(i));
                break;
            }
        }
        return me();
    }

    public ProductPage addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToBasket));
        click(addToBasket);
        return me();
    }

    public ProductPage returnToProductPage() {
        wait.until(ExpectedConditions.elementToBeClickable(returnToProductPage));
        click(returnToProductPage);
        return me();
    }
    public ProductPage openBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(goToBasket));
        click(goToBasket);
        return me();
    }
    public ProductPage goToMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(goToMainPage));
        click(goToMainPage);
        return me();
    }
}