package cpe.atelier1.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cpe.atelier1.model.Card;
import cpe.atelier1.serialization.CardSerializer;

public class CardJSONSerializer implements CardSerializer<ObjectNode> {
    @Override
    public ObjectNode serialize(Card card) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        node.put("name", card.getName());
        node.put("description", card.getDescription());
        node.put("family", card.getFamily());
        node.put("affinity", card.getAffinity());
        node.put("imgUrl", card.getImgUrl());
        node.put("smallImgUrl", card.getSmallImgUrl());
        node.put("id", card.getId());
        node.put("energy", card.getEnergy());
        node.put("hp", card.getHp());
        node.put("defence", card.getDefence());
        node.put("attack", card.getAttack());
        node.put("price", card.getPrice());
        node.put("userId", card.getUserId());

        return node;
    }

    @Override
    public Card deserialize(ObjectNode object) {
        String name = object.get("name").asText();
        String description = object.get("description").asText();
        String family = object.get("family").asText();
        String affinity = object.get("affinity").asText();
        String imgUrl = object.get("imgUrl").asText();
        String smallImgUrl = object.get("smallImgUrl").asText();
        int id = object.get("id").asInt(0);
        double energy = object.get("energy").asDouble(0.0);
        double hp = object.get("hp").asDouble(0.0);
        double defence = object.get("defence").asDouble(0.0);
        double attack = object.get("attack").asDouble(0.0);
        double price = object.get("price").asDouble(0.0);
        int userId = object.get("userId").asInt(0);

        return new Card(id, name, description,
                family, affinity,imgUrl,smallImgUrl,
                (float) energy, (float) hp, (float) defence, (float) attack,
                (float) price, userId);
    }
}
