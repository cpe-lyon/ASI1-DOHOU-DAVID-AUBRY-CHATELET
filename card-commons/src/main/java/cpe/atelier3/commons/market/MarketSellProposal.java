package cpe.atelier3.commons.market;


import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.user.User;

public record MarketSellProposal(Long id, Card card, User seller, double price) {
}
