package cpe.atelier1.controller.card;

import cpe.atelier1.domain.card.Card;
import org.springframework.stereotype.Component;

@Component
public class CardDtoMapper {

    public CardDTO CardToCardDTO(Card card) {
        return new CardDTO(card.getId(), card.getName(), card.getDescription(), card.getFamily(), card.getAffinity(),
                card.getImgUrl(), card.getSmallImgUrl(), card.getEnergy(), card.getHp(), card.getDefence(),
                card.getPrice(), card.getPrice());
    }
}
