package cpe.atelier3.commons.api.card.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CardURIUtils {
    @Autowired
    private DiscoveryClient discoveryClient;

    public URI getAllCardsURI() {
        return discoveryClient.getInstances("card-manager").get(0).getUri().resolve("/card/all");
    }

    public URI findCardById(Long id) {
        return discoveryClient.getInstances("card-manager").get(0).getUri().resolve("/card/" + id.toString());
    }
}
