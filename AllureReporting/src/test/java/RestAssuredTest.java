import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(AutomationListener.class)
public class RestAssuredTest {

    static {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    @Test(groups = {"JAK"})
    public void testelon() throws Exception {
        String url = "https://api.spacex.land/graphql/";
        JSONObject json = new JSONObject();
        json.put("query", "query ExampleQuery {\n  company {\n    ceo\n  }\n  roadster {\n    apoapsis_au\n  }\n}");
        Response verifyFarmerRes = given().filters(new RestAssuredRequestLogger(), new RestAssuredResponseLogger()).contentType("application/json").body(json.toJSONString()).post(url);
        Assert.assertTrue(false);
    }
}
