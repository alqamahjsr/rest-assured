

package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import data.restfulbooker.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.testng.annotations.Test;



@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class PostRequestBuilderExample extends SetupSpecification {

    @Test
    @Description ("Example of using Builder Pattern to pass test data in tests")
    @Severity (SeverityLevel.BLOCKER)
    @Story ("Builder Pattern Example using rest assured")
    public void postTestUsingBuilderPattern () {
        UserData userData = userDataBuilder ();
        given ().body (userData)
            .when ()
            .post ("/api/users")
            .then ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("name", equalTo (userData.getName ()))
            .body ("job", equalTo (userData.getJob ()));

    }

    private UserData userDataBuilder () {
        Faker faker = new Faker ();
        return UserData.builder ()
            .name (faker.name ()
                .firstName ())
            .job (faker.company ()
                .profession ())
            .build ();
    }

}