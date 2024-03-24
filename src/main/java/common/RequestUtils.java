package common;

import java.net.http.HttpResponse;

public class RequestUtils {

    public HttpResponse<String> sendRequest(String url, String method, String requestBody) {
        HttpResponse<String> response = null;
        Request request = new Request();
        if (method.equalsIgnoreCase(HttpMethod.GET.toString())) {
            response = request.sendGetRequest(url);

        } else if (method.equalsIgnoreCase(HttpMethod.POST.toString())) {
            response = request.sendPostRequest(url, requestBody);
        }

        return response;

    }

}
