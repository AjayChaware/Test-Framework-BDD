package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import testdata.TestDataBuild;
import utils.ApiUtils;
import utils.constants.ResourcePaths;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class MapsStepDefinitions extends ApiUtils {

    ResourcePaths resourcePaths;
    RequestSpecification requestDetails;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;


    @Given("Payload with AddPlaceApi")
    public void payloadWithAddPlaceApi() throws IOException {
        requestDetails = given()
                .spec(requestSpecification())
                .body(data.addPlacePayload());
    }

    @Given("Payload with AddPlaceApi with values {string}, {string},{string}")
    public void payloadWithAddPlaceApiWithValues(String name, String phoneNumber, String address) throws IOException {
        requestDetails = given()
                .spec(requestSpecification())
                .body(data.addPlacePayload(name, phoneNumber, address));

    }

    @When("user sends the Post request to {string} and method is {string}")
    public void userSendsThePostRequestToAndMethodIs(String apiRequest, String method) {
        resourcePaths = ResourcePaths.valueOf(apiRequest);
        System.out.println(resourcePaths.getPath());
        if (method.equalsIgnoreCase("Post")) {
            response = requestDetails.when()
                    .post(resourcePaths.getPath())
                    .then().spec(responseSpecification())
                    .extract().response();
        } else if (method.equalsIgnoreCase("Get")) {
            response = requestDetails.when()
                    .get(resourcePaths.getPath())
                    .then().spec(responseSpecification())
                    .extract().response();
        }

    }


    @Then("response is success with status code {int}")
    public void responseIsSuccessWithStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @And("{string} in response is {string}")
    public void inResponseIs(String key, String value) {
        Assert.assertEquals(getJsonValue(response, key), value);
    }


    @And("verify using {string} the place_id maps to the {string} created")
    public void verifyUsingThePlace_idMapsToTheCreated(String apiRequest, String name) throws IOException {
        place_id = getJsonValue(response, "place_id");
        requestDetails = given().spec(requestSpecification())
                .queryParam("place_id", place_id);

        userSendsThePostRequestToAndMethodIs(apiRequest, "get");

        Assert.assertEquals(name, getJsonValue(response, "name"));

    }

    @Given("Payload with DeletePlaceAPI")
    public void payloadWithDeletePlaceAPI() throws IOException {
        requestDetails = given().spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));

    }
}
