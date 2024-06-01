package cpe.atelier3.commons.api.auth;

import cpe.atelier3.commons.api.ApiUtils;
import cpe.atelier3.commons.api.auth.utils.AuthApiCookieUtils;
import cpe.atelier3.commons.api.auth.utils.AuthJWTUtils;
import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

@Component
@EnableDiscoveryClient
public class AuthenticationApi {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String checkAuthentication(String token) throws InvalidTokenException, URISyntaxException {
        String authUrl = discoveryClient.getInstances("card-auth").get(0).getUri().toString();

        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder.uri(new URI(authUrl).resolve("/auth/check"));
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
        return AuthJWTUtils.extractUIDFromToken(token);
    }
}
