package cpe.atelier2.domain.auth;

import cpe.atelier2.domain.auth.exception.ExpiredTokenException;
import cpe.atelier2.domain.auth.exception.IncorrectPasswordException;
import cpe.atelier2.domain.auth.exception.UserDoesNotExistException;
import cpe.atelier2.domain.auth.jwt.JWTGenerator;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static cpe.atelier2.domain.auth.jwt.JWTGenerator.JWT_KEY;
import static cpe.atelier2.domain.auth.jwt.JWTGenerator.TTL;

@Service
public class AuthenticationService {
    private final JWTGenerator jwtGenerator;

    private final UserRepository userRepository;

    public AuthenticationService(@Qualifier("default") JWTGenerator jwtGenerator, UserRepository userRepository, HttpSession httpSession) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    public Cookie authenticate(String username, String password) throws UserDoesNotExistException, IncorrectPasswordException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        if (! user.get().password().equals(password)) {
            throw new IncorrectPasswordException();
        }
        Cookie c =  new Cookie("token", jwtGenerator.generate(user.get()));
        c.setMaxAge((int) TTL/1000);
        return c;
    }

    public String checkAuthentication(String token) throws ExpiredTokenException {
        try {
            Jws<Claims> jwt = Jwts.parser()
                    .verifyWith(JWT_KEY)
                    .build()
                    .parseSignedClaims(token);
            return jwt.getPayload().get("user_id").toString();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }
}
