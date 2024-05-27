package cpe.atelier2.repository.card;

import cpe.atelier2.domain.card.Card;
import org.springframework.stereotype.Component;

@Component
public class CardEntityMapper {

    public Card cardEntityToCard(CardEntity cardEntity){
        return new Card(cardEntity.getId(), cardEntity.getName(), cardEntity.getDescription(), cardEntity.getFamily(),
                cardEntity.getAffinity(), cardEntity.getImgUrl(), cardEntity.getSmallImgUrl(),
                cardEntity.getEnergy(), cardEntity.getHp(), cardEntity.getDefence(), cardEntity.getAttack(),
                cardEntity.getPrice());
    }

    public CardEntity cardToCardEntity(Card card){
        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(card.getId());
        cardEntity.setName(card.getName());
        cardEntity.setHp(card.getHp());
        cardEntity.setAffinity(card.getAffinity());
        cardEntity.setDefence(card.getDefence());
        cardEntity.setAttack(card.getAttack());
        cardEntity.setEnergy(card.getEnergy());
        cardEntity.setDescription(card.getDescription());
        cardEntity.setFamily(card.getFamily());
        cardEntity.setImgUrl(card.getImgUrl());
        cardEntity.setSmallImgUrl(card.getSmallImgUrl());

        return cardEntity;
    }
}
