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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static cpe.atelier2.domain.auth.jwt.JWTGenerator.JWT_KEY;

@Service
public class AuthenticationService {
    private JWTGenerator jwtGenerator;

    private UserRepository userRepository;

    public AuthenticationService(@Qualifier("default") JWTGenerator jwtGenerator, UserRepository userRepository) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    public String authenticate(String username, String password) throws UserDoesNotExistException, IncorrectPasswordException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        if (! user.get().password().equals(password)) {
            throw new IncorrectPasswordException();
        }

        return jwtGenerator.generate(user.get());
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
