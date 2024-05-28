package cpe.atelier3.commons.api;

import cpe.atelier3.commons.api.exception.ApiNokException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiUtils {
    public static HttpResponse<String> sendRequestToApi(HttpRequest request, String apiName, String apiDescription) throws ApiNokException {
        HttpResponse<String> response;
        try {
            HttpClient client = HttpClient.newHttpClient();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new ApiNokException(response.statusCode(), apiName + " could not be contacted (" + apiDescription + ")");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
