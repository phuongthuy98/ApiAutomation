package StepDefinitions.CreateUserApi;

import common.JsonUtils;
import common.RequestUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.net.http.HttpResponse;

import static org.testng.Assert.assertEquals;

public class VerifyValidationSingleFieldSteps {

    String url, method;
    HttpResponse<String> response;
    String requestBody;
    JsonUtils jsonUtils = new JsonUtils();
    String requestBodyName;


    @When("I send request with request body include {string} and {string}")
    public void i_send_request_with_request_body_include(String inputFieldName, String inputValue) {
        RequestUtils requestUtils = new RequestUtils();
        requestBody = jsonUtils.readJsonFileWithChangedValue(requestBodyName, inputFieldName, inputValue);
        response = requestUtils.sendRequest(url, method, requestBody);

    }

    @Then("I get {int} and {string}")
    public void i_get_and_name_is_required(Integer expectedStatusCode, String expectedMessage) {
        assertEquals(response.statusCode(), expectedStatusCode);
        String responseBody = response.body();

        String actualMessage = jsonUtils.getValueByKey(responseBody, "message");
        assertEquals(actualMessage, expectedMessage);
    }

}
