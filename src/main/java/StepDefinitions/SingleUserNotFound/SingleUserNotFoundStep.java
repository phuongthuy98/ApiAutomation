package StepDefinitions.SingleUserNotFound;

import io.cucumber.java.en.Then;

import java.net.http.HttpResponse;

import static org.testng.Assert.assertEquals;

public class SingleUserNotFoundStep {
    HttpResponse<String> response;
    @Then("Response returns StatusCode")
    public void responseReturnsStatusCode() {
        assertEquals(response.statusCode(), 400);
    }
}
