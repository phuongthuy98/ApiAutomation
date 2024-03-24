package common;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Request {
    HttpRequest request;
    HttpResponse<String> response;

    public Request() {
        request =  null;
    }

    public HttpResponse<String> sendGetRequest(String url) {
        try {
            request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            response =  HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Send GET request incorrectly");
            e.printStackTrace();
        }
        return this.response;
    }

    public HttpResponse<String> sendPostRequest(String url, String requestBody) {
        try {
            request = HttpRequest.newBuilder().uri(new URI(url)).POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8)).build();
            response =  HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Send POST request incorrectly");
            e.printStackTrace();
        }
        return this.response;
    }
}