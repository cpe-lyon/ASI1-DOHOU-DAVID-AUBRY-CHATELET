package cpe.atelier3.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"cpe.atelier3.commons.user.entity", "cpe.atelier3.commons.card.entity", "cpe.atelier3.commons.market.entity"})
@ComponentScan({"cpe.atelier3.commons", "cpe.atelier3.auth"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
