package cpe.atelier3.commons.api.user.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class UserURIUtils {
    private static final String API_URL = System.getenv("USER_API_URL");

    public static URI findUserById(Long id) throws URISyntaxException {
        return new URI(API_URL + "/user/" + id.toString());
    }
}
