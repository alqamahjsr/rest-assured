
package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.reqres.AuthenticationPojo;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
@Story ("Perform Authentication using rest-assured")
public class TestAuthentication {

    private static final String URL = "https://reqres.in";
    private static final Logger LOG = LogManager.getLogger (TestAuthentication.class);

    
    @DataProvider
    public Iterator<Object[]> getAuthenticationData () {
        final List<Object[]> getTestData = new ArrayList<> ();
        getTestData.add (new Object[] { "eve.holt@reqres.in", "pistol" });
        return getTestData.iterator ();
    }

 
    @Test (dataProvider = "getAuthenticationData")
    @Description ("Example Test for performing authentication using rest assured")
    @Severity (SeverityLevel.NORMAL)
    public void testAuthenticationToken (String email, String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo (email, password);

        given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .log ()
            .all ()
            .post (URL + "/api/register")
            .then ()
            .assertThat ()
            .statusCode (200)
            .log ()
            .all ()
            .body ("id", notNullValue ())
            .and ()
            .body ("token", notNullValue ());

    }

  
    public static Map<String, Object> getToken (String email, String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo (email, password);
        final String response = given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .log ()
            .all ()
            .post (URL + "/api/register")
            .then ()
            .assertThat ()
            .statusCode (200)
            .log ()
            .all ()
            .body ("id", notNullValue ())
            .and ()
            .body ("token", notNullValue ())
            .and ()
            .extract ()
            .response ()
            .asString ();

        final JSONObject responseObject = new JSONObject (response);
        final Map<String, Object> responseMap = new HashMap<> ();
        responseMap.put ("id", responseObject.getInt ("id"));
        responseMap.put ("token", responseObject.getString ("token"));
        return responseMap;
    }

    @Test (dataProvider = "getAuthenticationData")
    @Severity (SeverityLevel.NORMAL)
    @Description ("Example Test for printing token by getting token after executing the post authentication request")
    public void testAuthToken (String email, String password) {
        LOG.info ("Token is" + getToken (email, password).get ("token")
            .toString ());

    }

}