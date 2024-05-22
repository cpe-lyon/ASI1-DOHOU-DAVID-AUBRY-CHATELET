package cpe.atelier1.repository.card;

import cpe.atelier1.domain.card.Card;

public class CardEntityMapper {

    public Card cardEntityToCard(CardEntity cardEntity){
        return new Card(cardEntity.getId(), cardEntity.getName(), cardEntity.getDescription(), cardEntity.getFamily(),
                cardEntity.getAffinity(), cardEntity.getImgUrl(), cardEntity.getSmallImgUrl(),
                cardEntity.getEnergy(), cardEntity.getHp(), cardEntity.getDefence(), cardEntity.getAttack(),
                cardEntity.getPrice());
    }
}
