package cpe.atelier3.commons.api.card.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class CardURIUtils {
    private static final String API_URL = System.getenv("CARD_API_URL");

    public static URI getAllCardsURI() throws URISyntaxException {
        return new URI(API_URL + "/card/all");
    }

    public static URI findCardById(Long id) throws URISyntaxException {
        return new URI(API_URL + "/card/" + id.toString());
    }
}
