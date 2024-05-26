package cpe.atelier1.services;

import cpe.atelier1.model.Card;
import cpe.atelier1.model.dao.CardDao;
import cpe.atelier1.model.dto.CardFormDTO;
import cpe.atelier1.model.dto.CardSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Card> searchCard(CardSearchDTO cardSearch) {
        return Optional.ofNullable(cardDao.getCardByName(cardSearch.getCardName()));
    }
}
