package regression;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static propertyUtils.PropertyReader.getProperties;
import static io.restassured.RestAssured.baseURI;

public class APITests extends BaseTest {
    List<Map> productList = new ArrayList<>();
    List<Map> cityList = new ArrayList<>();

    @BeforeTest
    public void preconditions() {
        baseURI = getProperties().getProperty("url");
    }

    @Test(priority = 1)
    public void menCategoryTest() {
        Response response = given().basePath("/api/v1/recommendations/section").param("section", "main").param("limit", 30).param("gender", "men").get();
        Assert.assertEquals(response.statusCode(), 200);
        productList = response.jsonPath().getList("products", Map.class);
        Assert.assertNotEquals(productList.get(0).get("gender"), "women");
        Assert.assertNotEquals(productList.get(1).get("gender"), "women");
        Assert.assertNotEquals(productList.get(2).get("gender"), "women");
        Assert.assertNotEquals(productList.get(3).get("gender"), "women");
        Assert.assertNotEquals(productList.get(4).get("gender"), "women");

    }
    @Test(priority = 2)
    public void cityTest() {
        Response res1 = given().basePath("/apix/searchbyaoid/").param("aoid", "16733").get();
        res1.then().statusCode(200);
        cityList= res1.jsonPath().getList("", Map.class);
        Response res2 = given().basePath("/apix/searchbyaoid/").param("aoid",cityList.get(0).get("aoid")).get();
        res2.then().statusCode(200);
        Assert.assertEquals("["+cityList.get(0).get("aoid").toString()+"]", res2.jsonPath().getString("aoid"));
    }

    @Test (priority=3)
    public void cartTest(){
        Response response = given().basePath("/api/v1/recommendations/section").param("section","cart").param("limit", 30).param("gender","prism").get();
        response.then().statusCode(200);
        Assert.assertTrue(response.jsonPath().getList("products").size()>0);
    }

    @Test (priority=4)
    public void test(){
        Response response = given().basePath("/api/v1/cms/topmenu_flexible").contentType(ContentType.JSON)
                .body("{\n" +
                "    \"aoid\": \"16733\",\n" +
                "    \"gender\": \"men\",\n" +
                "    \"version\": \"default\",\n" +
                "    \"platform\": \"desktop_site\"\n" +
                "}").post();
        response.then().statusCode(200);
    }
}
