

package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;


@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestGetRequestWithRestAssuredSpecs extends SetupSpecification {

    @Test
    @Description ("Example Test for executing GET request using rest assured specification")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Writing API Tests using rest assured configurations")
    public void getRequestTestWithRestAssuredConfig () {
        given ().when ()
            .get ("/api/users/2")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("data.first_name", equalTo ("Janet"));
    }
}