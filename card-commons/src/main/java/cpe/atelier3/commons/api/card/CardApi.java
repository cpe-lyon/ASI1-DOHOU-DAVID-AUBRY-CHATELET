package cpe.atelier3.commons.api.card;

import com.google.gson.Gson;
import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.api.ApiUtils;
import cpe.atelier3.commons.api.card.utils.CardURIUtils;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.user.User;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class CardApi {

    private static final String API_NAME = "CardAPI";

    public List<Card> getRandomStarterCards(int i) throws URISyntaxException, ApiNokException {
        Gson gson = new Gson();
        List<Card> cards;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(CardURIUtils.getAllCardsURI())
                .GET()
                .build();
        HttpResponse<String> response = ApiUtils.sendRequestToApi(request, API_NAME, "get all cards");
        cards = List.of(gson.fromJson(response.body(), Card[].class));

        return cards;
    }

    public Card findCardById(Long id) throws CardNotFoundException, URISyntaxException {
        Gson gson = new Gson();
        Card card;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(CardURIUtils.findCardById(id))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
             response = ApiUtils.sendRequestToApi(request, API_NAME, "find card by id");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == 404) {
                throw new CardNotFoundException();
            }
        }

        return gson.fromJson(response.body(), Card.class);
    }
}
