package cpe.atelier3.commons.api.auth.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class AuthApiURIUtils {
    public static final String AUTH_API_URL = System.getenv("AUTH_API_URL");


    public static URI getAuthenticationCheckUri() throws URISyntaxException {
        return new URI(AUTH_API_URL + "/auth/check");
    }
}
