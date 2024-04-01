package StepDefinitions.ListUserApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JsonUtils;
import common.RequestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullyStep {
	String url, method;
	HttpResponse<String> response;
	String requestBody;

	@Given("I have valid URL and method")
	public void i_have_valid_url_and_method(List<Map<String, String>> givenTable) {
		Map<String, String> row1 = givenTable.get(0);
		url = row1.get("URL");
		method = row1.get("Method").trim();
	}

	@Given("I have valid request body")
	public void i_have_valid_request_body(List<Map<String, String>> givenTable) {
		//requestBody = "{\n" + "    \"name\": \"morpheus\",\n" + "    \"job\": \"leader\"\n" + "}";
		Map<String, String> row1 = givenTable.get(0);
		String requestBodyName = row1.get("RequestBodyName").trim();
		JsonUtils jsonUtils = new JsonUtils();
		requestBody = jsonUtils.readJsonFile(requestBodyName);


	}

	@When("I send request with request body")
	public void i_send_request_with_request_body() {
		RequestUtils requestUtils = new RequestUtils();
		response = requestUtils.sendRequest(url, method, requestBody);
	}

	@When("I send request")
	public void i_send_request() {
		RequestUtils requestUtils = new RequestUtils();
		response = requestUtils.sendRequest(url, method, "");
	}

	@Then("I get {int}")
	public void i_get_status_and_response(int statusCode) {
		assertEquals(response.statusCode(), statusCode);
	}

	@Then("I get response body with {int}")
	public void i_get_response_body_with_page(int page) {
		String responseBody = response.body();
		JsonUtils jsonUtils = new JsonUtils();
		int actualPageNumber = Integer.parseInt(jsonUtils.getValueByKey(responseBody, "page"));
		assertEquals(actualPageNumber, page);
		ArrayList<String> ids = jsonUtils.getValueByArrayKey(responseBody, "data", "id");
		assertEquals(ids.get(0),"7");
		assertEquals(ids.get(1),"8");
		assertEquals(ids.get(2),"9");
	}

}
