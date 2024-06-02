package cpe.atelier3.auth.domain.auth.jwt;

import cpe.atelier3.commons.user.User;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public interface JWTGenerator {
    SecretKey JWT_KEY = new
            SecretKeySpec("verysecurejwtsecretverysecurejwtsecretverysecurejwtsecret".getBytes(),
            SignatureAlgorithm.HS256.getJcaName());
    String EXPIRATION_CLAIM = "expires_at";

    /**
     * TTL Token JWT (ms, 1h)
     */
    long TTL = 60 * 60 * 1000;

    String generate(User user);
}