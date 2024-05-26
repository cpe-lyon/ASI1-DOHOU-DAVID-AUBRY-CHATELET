package cpe.atelier2.controller.market;

import cpe.atelier2.controller.card.CardDTO;
import cpe.atelier2.controller.user.PublicUserDTO;

public record MarketSellProposalDto(Long id, CardDTO card, PublicUserDTO seller, double price) {
}
