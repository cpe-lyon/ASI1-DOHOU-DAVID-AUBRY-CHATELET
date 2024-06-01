package cpe.atelier3.commons.api.user.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class UserURIUtils {
    @Autowired
    DiscoveryClient discoveryClient;

    public URI findUserById(Long id) {
        return discoveryClient.getInstances("card-auth").get(0)
                .getUri()
                .resolve("/user/" + id.toString());
    }

    public URI privateUserInfo() {
        return discoveryClient.getInstances("card-auth").get(0)
                .getUri()
                .resolve("/user/private");
    }
}
