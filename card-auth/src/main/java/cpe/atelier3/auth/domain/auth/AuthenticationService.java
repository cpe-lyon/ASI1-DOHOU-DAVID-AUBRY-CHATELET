package cpe.atelier3.auth.domain.auth;

import cpe.atelier3.auth.domain.auth.exception.ExpiredTokenException;
import cpe.atelier3.auth.domain.auth.exception.IncorrectPasswordException;
import cpe.atelier3.auth.domain.auth.exception.UserDoesNotExistException;
import cpe.atelier3.auth.domain.auth.jwt.JWTGenerator;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.auth.repository.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Optional;


import static cpe.atelier3.auth.domain.auth.jwt.JWTGenerator.JWT_KEY;
import static cpe.atelier3.auth.domain.auth.jwt.JWTGenerator.TTL;

@Service
public class AuthenticationService {
    private final HttpSession httpSession;
    private JWTGenerator jwtGenerator;

    private UserRepository userRepository;

    public AuthenticationService(@Qualifier("default") JWTGenerator jwtGenerator, UserRepository userRepository, HttpSession httpSession) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.httpSession = httpSession;
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

    public void checkAuthentication(String token) throws ExpiredTokenException {
        try {
            Jwts.parser()
                    .verifyWith(JWT_KEY)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }
}
