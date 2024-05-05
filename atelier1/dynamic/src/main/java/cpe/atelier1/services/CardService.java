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


    @Autowired
    CardDao cardDao;

    public List<Card> getCards() {
        return cardDao.getCardList();
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
        return cardDao.addCard(card);
    }


}
