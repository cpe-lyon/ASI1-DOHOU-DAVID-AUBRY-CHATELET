package cpe.atelier3.manager.domain.card;


import cpe.atelier3.commons.card.Card;

import java.util.List;
import java.util.Optional;

public interface ICardRepository {

    List<Card> findAll();

    Optional<Card> findByName(String name);

    void addNewCard(Card card);

    Optional<Card> findById(Long id);
}
