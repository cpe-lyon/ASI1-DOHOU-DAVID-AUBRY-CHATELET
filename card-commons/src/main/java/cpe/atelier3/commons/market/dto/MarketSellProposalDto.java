package cpe.atelier3.commons.market.dto;


import cpe.atelier3.commons.card.dto.CardDTO;
import cpe.atelier3.commons.user.dto.PublicUserDTO;

public record MarketSellProposalDto(Long id, CardDTO card, PublicUserDTO seller, double price) {
}
