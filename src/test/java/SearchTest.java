import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchResultPage;
import pageObjects.baseObjects.BaseTest;

public class SearchTest extends BaseTest {
    @Test(dataProvider = "item")
    public void searchingResultsTest(String item, String status) {
        get(HomePage.class)
                .open()
                .waitUntilPageLoaded()
                .enterSearchItem(item);
        if (status.equals("positive")) {
            get(SearchResultPage.class)
                    .waitUntilPageLoaded()
                    .positiveSearchResultCheck(item);
        } else {
            get(SearchResultPage.class)
                    .waitUntilPageLoaded()
                    .negativeSearchResultCheck(item);
        }
    }
    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {"1234567", "negative"},
                {"HGhgHGhg", "negative"},
                {"h", "positive"},
                {"Шорты", "positive"}
        };
    }
}
