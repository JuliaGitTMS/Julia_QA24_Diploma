package pageObjects;

import org.openqa.selenium.By;
import entities.Product;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BaseLoadedPage;


public class SearchResultPage extends BaseLoadedPage<SearchResultPage> {
    private final By notFoundInfo = By.xpath("//h1");
    private final By foundInfo = By.xpath("//h2");
    private final By productList = By.className("x-product-card__hit-area");

    public SearchResultPage negativeSearchResultCheck(String item) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(notFoundInfo));
        wait.until(ExpectedConditions.textToBe(notFoundInfo, "Ничего не нашли по запросу «" + item.toLowerCase() + "»"));
        return me();
    }

    public SearchResultPage positiveSearchResultCheck(String item) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(foundInfo));
        Assert.assertTrue(driver.findElement(foundInfo).getText().toLowerCase().contains(item.toLowerCase()));
        Assert.assertFalse(driver.findElements(productList).isEmpty());
        return me();
    }

    public SearchResultPage chooseProductCategory(Product product) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains (text(), '" + product.getCategory() + "')]")));
        click(By.xpath("//span[contains (text(), '" + product.getCategory() + "')]"));
        return me();
    }

    public SearchResultPage chooseProductCategory(String category) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains (text(), '" + category + "')]")));
        click(By.xpath("//span[contains (text(), '" + category + "')]"));
        return me();
    }

    public SearchResultPage goToItemPage(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productList));
        click(driver.findElements(productList).get(index).findElement(By.tagName("a")));
        return me();
    }
}
