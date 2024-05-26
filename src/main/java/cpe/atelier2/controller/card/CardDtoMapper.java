package cpe.atelier2.controller.card;

import cpe.atelier2.domain.card.Card;
import org.springframework.stereotype.Component;

@Component
public class CardDtoMapper {

    public CardDTO cardToCardDTO(Card card) {
        return new CardDTO(card.getId(), card.getName(), card.getDescription(), card.getFamily(), card.getAffinity(),
                card.getImgUrl(), card.getSmallImgUrl(), card.getEnergy(), card.getHp(), card.getDefence(),
                card.getPrice(), card.getPrice());
    }

    public Card cardDtoToCard(CardDTO cardDTO){
        return new Card(cardDTO.getId(), cardDTO.getName(), cardDTO.getDescription(), cardDTO.getFamily(), cardDTO.getAffinity(),
                cardDTO.getImgUrl(), cardDTO.getSmallImgUrl(), cardDTO.getEnergy(), cardDTO.getHp(), cardDTO.getDefence(),
                cardDTO.getPrice(), cardDTO.getPrice());
    }
}
