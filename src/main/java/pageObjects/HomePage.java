package pageObjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.baseObjects.BaseLoadedPage;
import entities.Product;

import static driver.Driver.getDriver;
import static pageObjects.ConstantItems.*;
import static propertyUtils.PropertyReader.getProperties;

@Log4j
public class HomePage extends BaseLoadedPage<HomePage> {
    private final By search = By.xpath("//div[@class='_root_1su1z_2']/input");

    public HomePage open() {
        getDriver().get(getProperties().getProperty("url"));
        return me();
    }

    public HomePage cookiesLinkCheck() {
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(COOKIELINK)));
        click(getConstantItemLocator(COOKIELINK));
        wait.until(ExpectedConditions.urlToBe("https://www.lamoda.by/lp/cookie/"));
        return me();
    }

    public HomePage acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(ACCEPTCOOKIES)));
        click(getConstantItemLocator(ACCEPTCOOKIES));
        return me();
    }

    public HomePage rejectCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(REJECTCOOKIES)));
        click(getConstantItemLocator(REJECTCOOKIES));
        return me();
    }

//    public HomePage enterSearchItem(String item) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(getConstantItemLocator(SEARCHINPUT)));
//        sendKeys(getConstantItemLocator(SEARCHINPUT), item);
//        click(getConstantItemLocator(SEARCHBUTTON));
//        return me();
//    }
//    public HomePage enterSearchItem(Product product) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(getConstantItemLocator(SEARCHINPUT)));
//        sendKeys(getConstantItemLocator(SEARCHINPUT), product.getName());
//        click(getConstantItemLocator(SEARCHBUTTON));
//        return me();
//    }
//        public HomePage enterSearchItem(String item) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_root_1su1z_2']/input")));
//        sendKeys(driver.findElement(By.xpath("//div[@class='_root_1su1z_2']/input")), item);
//        click(driver.findElement(By.xpath("//div[@class='_root_1su1z_2']/button")));
//        return me();
//    }

    public HomePage cityChoiseCheck(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(getConstantItemLocator(CITYLINK)));
        click(getConstantItemLocator(CITYLINK));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getConstantItemLocator(CITYLIST)));
        String city = driver.findElements(getConstantItemLocator(CITYLIST)).get(index).getText();
        click(driver.findElements(getConstantItemLocator(CITYLIST)).get(index));
        click(getConstantItemLocator(CITYCLOSE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(getConstantItemLocator(CITYNAME), city));
        return me();
    }

}
