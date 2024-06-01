package cpe.atelier3.commons.api.user;

import com.google.gson.Gson;
import cpe.atelier3.commons.api.ApiUtils;
import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.api.user.utils.UserURIUtils;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class UserApi {

    public static final String API_NAME = "CardAPI";

    @Autowired
    UserURIUtils userURIUtils;

    public User findUserById(Long id) throws UserNotFoundException {
        Gson gson = new Gson();
        HttpRequest request;
        request = HttpRequest.newBuilder()
            .uri(userURIUtils.findUserById(id))
            .GET()
            .build();


        HttpResponse<String> response = null;
        try {
            response = ApiUtils.sendRequestToApi(request, API_NAME, "Get user");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == 404) {
                throw new UserNotFoundException();
            }
        }

        assert response != null;
        return gson.fromJson(response.body(), User.class);
    }
}
