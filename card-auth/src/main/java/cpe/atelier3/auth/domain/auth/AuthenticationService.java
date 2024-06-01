package cpe.atelier3.auth.domain.auth;

import cpe.atelier3.auth.domain.user.IUserRepository;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import cpe.atelier3.commons.user.exception.IncorrectPasswordException;
import cpe.atelier3.commons.user.exception.UserDoesNotExistException;
import cpe.atelier3.auth.domain.auth.jwt.JWTGenerator;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.auth.repository.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Optional;


import static cpe.atelier3.auth.domain.auth.jwt.JWTGenerator.JWT_KEY;
import static cpe.atelier3.auth.domain.auth.jwt.JWTGenerator.TTL;

@Service
public class AuthenticationService {

    private final JWTGenerator jwtGenerator;

    private final IUserRepository userRepository;

    public AuthenticationService(@Qualifier("default") JWTGenerator jwtGenerator, @Autowired IUserRepository userRepository) {
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

    public String checkAuthentication(String token) throws InvalidTokenException {
        try {
            return Jwts.parser()
                    .verifyWith(JWT_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("user_id").toString();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException();
        }
    }
}
