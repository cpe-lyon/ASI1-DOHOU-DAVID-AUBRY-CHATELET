package cpe.atelier1.model.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cpe.atelier1.exception.UnexpectedJsonTypeException;
import cpe.atelier1.model.Card;
import cpe.atelier1.serialization.CardSerializer;
import cpe.atelier1.serialization.impl.CardJSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Repository
public class CardDao {
    Logger logger = LoggerFactory.getLogger(CardDao.class);

    private final Random randomGenerator;

    public CardDao() {
        randomGenerator = new Random();
    }

    public List<Card> getCardList() {
        CardSerializer<ObjectNode> serializer = new CardJSONSerializer();

        List<Card> cards = new ArrayList<>();
        JsonNode node = null;
        try {
            node = getCardsFromJson();
        } catch (IOException | UnexpectedJsonTypeException e) {
            throw new DataRetrievalFailureException(e.getMessage(), e);
        }

        node.forEach(jsonNode -> {
            if (!jsonNode.isObject()) {
                logger.error("Expected object from cards json but got {}", jsonNode.getNodeType().toString());
                return;
            }
            cards.add(serializer.deserialize((ObjectNode) jsonNode));
        });
        return cards;
    }

    public Card getCardByName(String name) {
        for (Card card : this.getCardList()) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    public Card getRandomCard() {
        List<Card> cards = this.getCardList();
        if (cards.isEmpty()) {
            return null;
        }
        int index = randomGenerator.nextInt(cards.size());
        return cards.get(index);
    }

    public Card addCard(int id, String name, String description, String family, String affinity,
                        String imgUrl, String smallImgUrl, float energy, float hp, float defence,
                        float attack, float price, int userId) {
        Card p = new Card(id, name, description, family, affinity, imgUrl, smallImgUrl, energy,
                hp, defence, attack, price, userId);
        this.addCard(p);
        return p;
    }

    public Optional<Exception> addCard(Card card) {
        ArrayNode array;
        try {
            array = (ArrayNode) getCardsFromJson();
        } catch (IOException | UnexpectedJsonTypeException e) {
            throw new DataRetrievalFailureException("Failed to retrieve cards from JSON", e);
        }
        CardSerializer<ObjectNode> serializer = new CardJSONSerializer();

        array.add(serializer.serialize(card));

        try {
            writeToJsonFile(array);
        } catch (IOException e) {
            logger.error("An error occured while writing json file", e);
            return Optional.of(new Exception("An error occured while writing card to persistent storage, please contact the administrator"));
        }

        return Optional.empty();
    }

    private JsonNode getCardsFromJson() throws IOException, UnexpectedJsonTypeException {
        InputStream JSONStream = getClass().getClassLoader()
                .getResourceAsStream("cards.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;

        node = mapper.readTree(JSONStream);

        if (!node.isArray()) {
            throw new UnexpectedJsonTypeException();
        }

        return node;
    }

    private void writeToJsonFile(JsonNode node) throws IOException {
        String JSONPath = Objects.requireNonNull(getClass().getClassLoader()
                .getResource("cards.json")).getPath();
        File JSONFile = new File(JSONPath);
        FileWriter fileWriter = new FileWriter(JSONFile);
        fileWriter.write(node.toString());
        fileWriter.close();
    }
}

