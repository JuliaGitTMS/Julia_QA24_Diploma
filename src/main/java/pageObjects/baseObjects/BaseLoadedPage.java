package pageObjects.baseObjects;

import entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.HomePage;
import pageObjects.SearchResultPage;

import static pageObjects.ConstantItems.*;

public class BaseLoadedPage <P> extends BasePage{
    private final By preloader = By.xpath("//*[@class='preloader__circle']");

    public P waitUntilPageLoaded() {
        waitUntil(1);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(preloader)));
        return me();
    }

    protected P me() {
        return (P) this;
    }

    public P enterSearchItem(String item) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getConstantItemLocator(SEARCHINPUT)));
        driver.findElement(getConstantItemLocator(SEARCHINPUT)).clear();
        sendKeys(getConstantItemLocator(SEARCHINPUT), item);
        click(getConstantItemLocator(SEARCHBUTTON));
        return me();
    }
    protected P enterSearchItem(Product product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getConstantItemLocator(SEARCHINPUT)));
        driver.findElement(getConstantItemLocator(SEARCHINPUT)).clear();
        sendKeys(getConstantItemLocator(SEARCHINPUT), product.getName());
        click(getConstantItemLocator(SEARCHBUTTON));
        return me();
    }
    public P goToWomenCategoryPage(){
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(WOMEN)));
        click(getConstantItemLocator(WOMEN));
        return me();
    }
    public P goToMenCategoryPage(){
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(MEN)));
        click(getConstantItemLocator(MEN));
        return me();
    }
    public P goToKidsCategoryPage(){
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(KIDS)));
        click(getConstantItemLocator(KIDS));
        return me();
    }
    public P goToShoppingCart(){
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(BASKET)));
        click(getConstantItemLocator(BASKET));
        return me();
    }
    protected Integer getElementCount (By by){
        return driver.findElements(by).size();
    }
    protected Boolean elementExistStatus (By by){
        return driver.findElements(by).size()!=0;
    }

}
