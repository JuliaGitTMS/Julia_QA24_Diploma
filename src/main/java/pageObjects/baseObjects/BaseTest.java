package pageObjects.baseObjects;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testngUtils.InvokedListener;
import testngUtils.Listener;

import java.lang.reflect.InvocationTargetException;

import static driver.Driver.createDriver;
import static driver.Driver.quitDriver;
import static driver.DriverType.CHROME;
import static driver.DriverType.valueOf;
import static propertyUtils.PropertyReader.getProperties;

@Listeners({Listener.class, InvokedListener.class})
public class BaseTest {
    @BeforeTest
    public void startUp() {
        createDriver(System.getProperties().containsKey("config")
                ? valueOf(getProperties().getProperty("browser").toUpperCase())
                : CHROME);
    }
    protected <T> T get(Class<T> page) {
        T instance;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
    @AfterTest
    public void tearDown() {
        quitDriver();
    }
}
