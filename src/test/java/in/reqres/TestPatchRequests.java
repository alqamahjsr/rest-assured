

package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import data.reqres.PostData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestPatchRequests {

    private static final Logger LOG = LogManager.getLogger (TestPatchRequests.class);
    private static final String URL = "https://reqres.in";

    @DataProvider (name = "patchData")
    public Iterator<Object[]> patchData () {
        final List<Object[]> patchData = new ArrayList<> ();
        patchData.add (new Object[] { 2, "Michael", "QA Lead" });
        patchData.add (new Object[] { 958, "Yuan", "Project Architect" });
        return patchData.iterator ();
    }

    @Test (dataProvider = "patchData")
    @Description ("Example Test for executing PATCH request using rest assured ")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Patch requests using rest-assured")
    public void patchRequestTests (final int id, final String name, final String job) {

        final PostData postData = new PostData (name, job);
        final String response = given ().contentType (ContentType.JSON)
            .body (postData)
            .when ()
            .patch (URL + "/api/users/" + id)
            .then ()
            .assertThat ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("name", equalTo (name))
            .and ()
            .assertThat ()
            .body ("job", equalTo (job))
            .and ()
            .extract ()
            .response ()
            .body ()
            .asString ();

        LOG.info (response);

    }

}