package cpe.atelier3.commons.api.auth;

import cpe.atelier3.commons.api.ApiUtils;
import cpe.atelier3.commons.api.auth.utils.AuthApiCookieUtils;
import cpe.atelier3.commons.api.auth.utils.AuthApiURIUtils;
import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.net.http.HttpRequest;

@Component
public class AuthenticationApi {

    public void checkAuthentication(String token) throws InvalidTokenException, URISyntaxException {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder.uri(AuthApiURIUtils.getAuthenticationCheckUri());
        builder.POST(HttpRequest.BodyPublishers.noBody());
        builder.setHeader("Cookie", AuthApiCookieUtils.formatToken(token));
        HttpRequest request = builder.build();

        try {
            ApiUtils.sendRequestToApi(request, "authentication", "check authentication");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == HttpStatus.UNAUTHORIZED.value()) {
                throw new InvalidTokenException();
            }
            throw new RuntimeException(e);
        }
    }
}
