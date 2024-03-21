package pageObjects.baseObjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import pageObjects.ConstantItems;

import static driver.Driver.getDriver;

@Log4j
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    {
        driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
    }
    protected void navigateTo(String url) {
//        log.info("Navigate to :: " + url);
        driver.get(url);
    }
    protected void waitUntil(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected void click(By by) {
//        log.info("Click on element::" + driver.findElement(by));
        driver.findElement(by).click();
    }

    protected void click(WebElement element) {
//        log.info("Click on element::" + element);
        element.click();
    }
    protected void sendKeys(By by, CharSequence... charSequences) {
//        log.info("Enter in element::" + driver.findElement(by) + " next keys: " + Arrays.toString(charSequences));
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(charSequences);
    }

    protected void sendKeys(WebElement element, CharSequence... charSequences) {
//        log.info("Enter in element::" + element + " next keys: " + Arrays.toString(charSequences));
        element.clear();
        element.sendKeys(charSequences);
    }
    protected By getConstantItemLocator (ConstantItems item){
        return By.xpath(item.getItemXpath());
    }
}
