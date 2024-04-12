package StepDefinitions.CreateUserApi;

import common.JsonUtils;
import common.RequestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class VerifyValidationSingleFieldSteps {

    String url, method;
    HttpResponse<String> response;
    String requestBody;
    JsonUtils jsonUtils = new JsonUtils();
    String requestBodyName;
    @Given("I have valid URL and method and request body")
    public void i_have_valid_url_and_method(List<Map<String, String>> givenTable) {
        Map<String, String> row1 = givenTable.get(0);
        url = row1.get("URL");
        method = row1.get("Method").trim();
        requestBodyName = row1.get("RequestBodyName").trim();
    }

    @When("I send request with request body include {string} and {string}")
    public void i_send_request_with_request_body_include(String inputFieldName, String inputValue) {
        RequestUtils requestUtils = new RequestUtils();
        requestBody=  jsonUtils.readJsonFileWithChangedValue(requestBodyName, inputFieldName, inputValue);
        response = requestUtils.sendRequest(url, method, requestBody);
    }

    @Then("I get {int} and {string}")
    public void i_get_and_name_is_required(int expectedStatusCode, String expectedMessage) {
        assertEquals(response.statusCode(), expectedStatusCode);
        String responseBody = response.body();

        String actualMessage = jsonUtils.getValueByKey(responseBody, "message");
        assertEquals(actualMessage, expectedMessage);
    }
}
