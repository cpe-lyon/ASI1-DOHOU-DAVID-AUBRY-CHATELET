package cpe.atelier3.commons.api.auth.utils;

public class AuthApiCookieUtils {
    public static String formatToken(String token) {
        return String.format("token=%s", token);
    }
}
