package cpe.atelier3.commons.user.dto;

import cpe.atelier3.commons.card.Card;

import java.util.List;

public record PrivateUserDTO(Long id, String email, String username, List<Card> cardsOfUser, double money) {
}