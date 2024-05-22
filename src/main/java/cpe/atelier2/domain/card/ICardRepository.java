package cpe.atelier2.domain.card;

import cpe.atelier2.repository.card.CardEntity;

import java.util.List;

public interface ICardRepository {

    List<Card> findAll();

    Card findByName(String name);

    void addNewCard(Card card);

}
