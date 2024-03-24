package StepDefinitions.ListUserApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import common.RequestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullyStep {
	String url, method;
	HttpResponse<String> response;

	@Given("I have valid URL and method")
	public void i_have_valid_url_and_method(List<Map<String, String>> givenTable) {
		Map<String, String> row1= givenTable.get(0);
		url = row1.get("URL");
		method = row1.get("Method").trim();
	}

	@When("I send request")
	public void i_send_request() {
		RequestUtils requestUtils = new RequestUtils();
		requestUtils.sendRequest(url, method, "");

	}

	@Then("I get status code and response")
	public void i_get_status_code_and_response() {
		assertEquals(response.statusCode(),200);
	}
}
