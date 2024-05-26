package cpe.atelier2.domain.user;

import cpe.atelier2.repository.card.CardEntity;

import java.util.List;

public record User(Long id, String email, String username, String password, double money, List<CardEntity> cardList) {
}
