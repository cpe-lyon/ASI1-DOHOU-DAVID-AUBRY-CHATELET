package cpe.atelier3.commons.user;

import cpe.atelier3.commons.card.Card;

import java.util.List;

public record User(Long id, String email, String username, String password, double money, List<Card> cardList) {
}
