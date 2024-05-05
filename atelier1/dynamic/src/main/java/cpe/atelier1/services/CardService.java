package cpe.atelier1.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cpe.atelier1.model.dao.CardDao;
import cpe.atelier1.model.Card;
import cpe.atelier1.model.dto.CardFormDTO;
import cpe.atelier1.serialization.CardSerializer;
import cpe.atelier1.serialization.impl.CardJSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // TODO: utiliser l'injection de dépendance pour charger les cartes depuis le JSON (DAO)
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

    public Optional<Exception> addCard(CardFormDTO cardForm) {
        Card card = new Card(0, cardForm.getName(),
                cardForm.getDescription(), cardForm.getFamily(),
                cardForm.getAffinity(), cardForm.getImgUrl(), cardForm.getSmallImgUrl(),
                cardForm.getEnergy(), cardForm.getHp(), cardForm.getDefence(), cardForm.getAttack(),
                cardForm.getPrice(), cardForm.getUserId());
        CardSerializer<ObjectNode> serializer = new CardJSONSerializer();
        // TODO: Utiliser l'injection de dépendance pour sauvegarder les cartes (DAO)
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
            return Optional.of(new Exception("Expected array from cards json but got " + node.getNodeType().toString()));
        }

        ArrayNode array = (ArrayNode) node;
        array.add(serializer.serialize(card));


        try {
            writeToJsonFile(array);
        } catch (IOException e) {
            logger.error("An error occured while writing json file", e);
            return Optional.of(new Exception("An error occured while writing card to persistent storage, please contact the administrator"));
        }

        return Optional.empty();
    }

    private void writeToJsonFile(JsonNode node) throws IOException {
        String JSONPath = getClass().getClassLoader()
                .getResource("cards.json").getPath();
        File JSONFile = new File(JSONPath);
        FileWriter fileWriter = new FileWriter(JSONFile);
        fileWriter.write(node.toString());

        fileWriter.close();
    }
}
