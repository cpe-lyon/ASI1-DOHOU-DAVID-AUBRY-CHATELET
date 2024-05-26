package cpe.atelier2.domain.market;

import cpe.atelier2.domain.card.Card;
import cpe.atelier2.domain.user.User;

public record MarketSellProposal(Long id, Card card, User seller, double price) {
}
