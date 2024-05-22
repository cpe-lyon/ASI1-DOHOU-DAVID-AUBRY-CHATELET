package cpe.atelier1.repository.card;

import cpe.atelier1.domain.card.Card;
import cpe.atelier1.domain.card.ICardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepository implements ICardRepository {

    private final CardJpaRepository cardJpaRepository;

    private final CardEntityMapper cardEntityMapper;

    public CardRepository(CardJpaRepository cardJpaRepository, CardEntityMapper cardEntityMapper) {
        this.cardJpaRepository = cardJpaRepository;
        this.cardEntityMapper = cardEntityMapper;
    }

    public List<Card> findAll() {
        List<CardEntity> cardEntityList = cardJpaRepository.findAll();
        return cardEntityList.stream()
                .map(cardEntityMapper::cardEntityToCard)
                .toList();
    }
}
