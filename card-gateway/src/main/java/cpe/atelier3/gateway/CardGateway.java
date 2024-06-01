package cpe.atelier3.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class CardGateway {

    public static void main(String[] args) {
        SpringApplication.run(CardGateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").uri("lb://card-auth"))
                .route("user", r -> r.path("/user/**").uri("lb://CARD-AUTH"))
                .route("manager", r -> r.path("/card/**").uri("lb://CARD-MANAGER"))
                .route("user", r -> r.path("/market/**").uri("lb://CARD-MARKET"))
                .build();
        //@formatter:on
    }
}