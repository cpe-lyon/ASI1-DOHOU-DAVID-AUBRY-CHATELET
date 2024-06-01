package cpe.atelier3.commons.api.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthJWTUtils {

    public static String extractUIDFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("user_id").asString();
    }
}
