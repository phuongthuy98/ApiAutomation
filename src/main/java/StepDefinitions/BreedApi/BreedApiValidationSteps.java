package StepDefinitions.BreedApi;

import common.JsonUtils;
import common.RequestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class BreedApiValidationSteps {

    String url, method;
    HttpResponse<String> response;
    String requestBody;
    @Given("I have valid URL and method of Breed Api")
    public void i_have_valid_url_and_method_of_breed_api(List<Map<String, String>> givenTable) {
        Map<String, String> row1 = givenTable.get(0);
        url = row1.get("URL");
        method = row1.get("Method").trim();
    }

    @When("I send request {string} and {string}")
    public void i_send_request_with_request_body(String fieldName, String value) {
        RequestUtils requestUtils = new RequestUtils();
        String newUrl;
        if (value.equalsIgnoreCase("missing")){
            newUrl = url.replace(fieldName, "");
        } else {
            newUrl = url.replace(fieldName, value);
        }
        response = requestUtils.sendRequest(newUrl, method, requestBody);
    }
    @Then("I get {int} and {string}")
    public void i_get_response_body_with_page(Integer expectedStatusCode, String expectedMessage) {
        String responseBody = response.body();
        JsonUtils jsonUtils = new JsonUtils();
        String actualMessage = jsonUtils.getValueByKey(responseBody, "message");

        assertEquals(actualMessage, expectedMessage);
        assertEquals(response.statusCode(), expectedStatusCode);
    }
}
