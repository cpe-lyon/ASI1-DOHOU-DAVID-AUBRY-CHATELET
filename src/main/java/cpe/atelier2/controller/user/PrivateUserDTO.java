package cpe.atelier2.controller.user;

import cpe.atelier2.domain.card.Card;

import java.util.List;

public record PrivateUserDTO(Long id, String username, List<Card> cardsOfUser, double money) {
}
