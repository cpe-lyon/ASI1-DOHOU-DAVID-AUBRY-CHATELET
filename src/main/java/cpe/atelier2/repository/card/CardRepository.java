package cpe.atelier2.repository.card;

import cpe.atelier2.domain.card.Card;
import cpe.atelier2.domain.card.ICardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardRepository implements ICardRepository {

    private final CardJpaRepository cardJpaRepository;

    private final CardEntityMapper cardEntityMapper;

    public CardRepository(CardJpaRepository cardJpaRepository, CardEntityMapper cardEntityMapper) {
        this.cardJpaRepository = cardJpaRepository;
        this.cardEntityMapper = cardEntityMapper;
    }

    @Override
    public List<Card> findAll() {
        List<CardEntity> cardEntityList = cardJpaRepository.findAll();
        return cardEntityList.stream()
                .map(cardEntityMapper::cardEntityToCard)
                .toList();
    }

    @Override
    public Optional<Card> findByName(String name){
        CardEntity cardEntity = cardJpaRepository.findByName(name);
        if (cardEntity == null) {
            return Optional.empty();
        }
        return Optional.of(cardEntityMapper.cardEntityToCard(cardEntity));
    }

    @Override
    public void addNewCard(Card cardToAdd){
        cardJpaRepository.save(cardEntityMapper.cardToCardEntity(cardToAdd));
    }
}
