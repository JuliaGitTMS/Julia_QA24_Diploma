package testngUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import static propertyUtils.PropertyReader.setUpProperty;

public class Listener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        if (System.getProperties().containsKey("config")) {
            setUpProperty(System.getProperty("config"));
        }
    }
}
