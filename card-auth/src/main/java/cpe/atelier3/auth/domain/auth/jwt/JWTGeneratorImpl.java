package cpe.atelier3.auth.domain.auth.jwt;

import cpe.atelier3.commons.user.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Component(value="default")
public class JWTGeneratorImpl  implements JWTGenerator {

    @Override
    public String generate(User user) {
        Date now = new Date();
        return Jwts.builder().subject(user.username())
                .header()
                .and()
                .signWith(JWT_KEY)
                .claim("user_id", user.id())
                .expiration(new Date(now.getTime() + TTL))
                .issuedAt(now)
                .compact();
    }
}
