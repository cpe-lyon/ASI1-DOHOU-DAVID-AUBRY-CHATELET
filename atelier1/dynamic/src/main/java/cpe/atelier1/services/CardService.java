package cpe.atelier1.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cpe.atelier1.controller.CardDao;
import cpe.atelier1.model.Card;
import cpe.atelier1.serialization.CardSerializer;
import cpe.atelier1.serialization.impl.CardJSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    Logger logger = LoggerFactory.getLogger(CardService.class);
    @Autowired
    CardDao cardDao;

    public List<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        CardSerializer<ObjectNode> serializer = new CardJSONSerializer();
        InputStream JSONStream  = getClass().getClassLoader()
                .getResourceAsStream("cards.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = mapper.readTree(JSONStream);
        } catch (IOException e) {
            logger.error("An error occured while reading cards file, returning empty array", e);
        }

        assert node != null;
        if (! node.isArray()) {
            logger.error("Expected array from cards json but got {}", node.getNodeType().toString());
            return cards;
        }

        node.forEach(jsonNode -> {
            if (! jsonNode.isObject()) {
                logger.error("Expected object from cards json but got {}", jsonNode.getNodeType().toString());
                return;
            }
            cards.add(serializer.deserialize((ObjectNode) jsonNode));
        });

        return cards;
    }

    public Card getCard(int id) {
        return this.getCards().stream()
                .filter(card -> card.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
