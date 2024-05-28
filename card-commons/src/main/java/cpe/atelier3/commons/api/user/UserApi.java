package cpe.atelier3.commons.api.user;

import com.google.gson.Gson;
import cpe.atelier3.commons.api.ApiUtils;
import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.api.user.utils.UserURIUtils;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class UserApi {

    public static final String API_NAME = "CardAPI";

    public User findUserById(Long id) throws ApiURIException, UserNotFoundException {
        Gson gson = new Gson();
        List<User> cards;
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(UserURIUtils.findUserById(id))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new ApiURIException();
        }


        HttpResponse<String> response = null;
        try {
            response = ApiUtils.sendRequestToApi(request, API_NAME, "Get user");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == 404) {
                throw new UserNotFoundException();
            }
        }

        return gson.fromJson(response.body(), User.class);
    }
}
