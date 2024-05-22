package cpe.atelier2.domain.card;

import cpe.atelier2.repository.card.CardEntity;

import java.util.List;
import java.util.Optional;

public interface ICardRepository {

    List<Card> findAll();

    Optional<Card> findByName(String name);

    void addNewCard(Card card);

}
