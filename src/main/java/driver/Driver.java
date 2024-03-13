package driver;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import propertyUtils.PropertyReader;

import java.time.Duration;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void createDriver(DriverType driverType) {
        if (driver.get() == null) {
            switch (driverType) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(PropertyReader.getProperties().getProperty("browser.option").split(";"));
                    driver.set (new ChromeDriver(chromeOptions));
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments(PropertyReader.getProperties().getProperty("browser.option").split(";"));
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
            }
        }
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
