package cpe.atelier2.controller.market;

import cpe.atelier2.domain.user.User;
import cpe.atelier2.domain.card.Card;

public record MarketSellProposalDto(Long id, Card card, User seller, double price) {
}
