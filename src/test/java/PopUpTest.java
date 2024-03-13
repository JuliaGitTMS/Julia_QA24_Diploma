import org.testng.annotations.Test;
import pageObjects.CookiesRulesPage;
import pageObjects.HomePage;
import pageObjects.baseObjects.BaseTest;

public class PopUpTest extends BaseTest {
    @Test(priority = 1)
    public void acceptCookiesTest() {
        get(HomePage.class)
//                .open()
                .waitUntilPageLoaded()
                .cookiesLinkCheck();
        get(CookiesRulesPage.class)
                .waitUntilPageLoaded()
                .acceptCookies();
    }
    @Test
    public void cityWindowTest (){
        get(HomePage.class)
                .open()
                .waitUntilPageLoaded()
                .cityChoiseCheck(3);
    }
}
