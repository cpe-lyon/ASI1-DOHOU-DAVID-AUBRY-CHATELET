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

    public URI paymentToUser() {
        return discoveryClient.getInstances("card-auth").get(0)
                .getUri()
                .resolve("/user/private/payment");
    }

    public URI cardOfUserManipulation(Long uid, Long cid) {
        return discoveryClient.getInstances("card-auth").get(0)
                .getUri()
                .resolve("/user/" + uid.toString() + "/card/" + cid.toString());
    }

    public URI getCardOfUser(Long uid, Long cid) {
        return discoveryClient.getInstances("card-auth").get(0)
                .getUri()
                .resolve("/user/" + uid.toString() + "/card/" + cid.toString());
    }
}
