
package in.reqres;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestDeleteRequests {

    private static final String URL = "https://reqres.in/api/users/";

    
    @DataProvider (name = "deleteUserRestAssured")
    public Iterator<Object[]> deleteRestUsers () {
        final List<Object[]> deleteData = new ArrayList<> ();
        deleteData.add (new Object[] { 2 });
        return deleteData.iterator ();
    }

   
    @Test (dataProvider = "deleteUserRestAssured")
    @Description ("Example Test for executing DELETE request using rest assured")
    @Severity (SeverityLevel.NORMAL)
    @Story ("Execute Delete requests using rest-assured")
    public void deleteRequestTests (final int userId) {
        given ().when ()
            .delete (URL + userId)
            .then ()
            .assertThat ()
            .statusCode (204);
    }
}